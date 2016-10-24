package hu.unideb.inf.rft.restaurant.web.security;


import hu.unideb.inf.rft.restaurant.client.api.service.RoleService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.RoleVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@EJB(name = "UserService", beanInterface = UserService.class)
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @EJB
    UserService userService;
    @EJB
    RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        UserVo user;
        try {
            user = userService.getUserByName(username);

            if (user == null) {
                throw new UsernameNotFoundException(username);
            }

            List<RoleVo> roles = roleService.getRolesByUserId(user.getId());
            List<GrantedAuthority> authorities = buildUserAuthority(roles);

            return buildUserForAuthentication(user, authorities);

        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(e.getMessage());
        }

    }

    private User buildUserForAuthentication(UserVo user, List<GrantedAuthority> authorities) {
        return new User(user.getName(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<RoleVo> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        for (RoleVo userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getName()));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

}
