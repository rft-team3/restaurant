package hu.unideb.inf.rft.restaurant.web.managedbeans.admin;

import hu.unideb.inf.rft.restaurant.client.api.service.RoleService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.RoleVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "addRoleToUsers")
@RequestScoped
public class AddRoleToUsersMB {

    @EJB
    private UserService userService;
    @EJB
    private RoleService roleService;

    public void addRole(List<UserVo> users, String role) {
        RoleVo roleVo = roleService.getRoleByName(role);

        for (UserVo userVo : users) {
            userService.addRoleToUserByName(userVo.getName(), roleVo);
        }
    }

    public void removeRole(List<UserVo> users, String role) {
        RoleVo roleVo = roleService.getRoleByName(role);

        for (UserVo userVo : users) {
            userService.removeRoleFromUserByName(userVo.getName(), roleVo);
        }
    }
}
