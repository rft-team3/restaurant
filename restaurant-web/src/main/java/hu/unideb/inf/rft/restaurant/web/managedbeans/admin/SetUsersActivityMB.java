package hu.unideb.inf.rft.restaurant.web.managedbeans.admin;

import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "setUsersActivity")
@RequestScoped
public class SetUsersActivityMB {

    @EJB
    private UserService userService;

    public void setActivity(List<UserVo> users, boolean activity) {
        for (UserVo userVo : users) {
            userService.setUserActivityByName(userVo.getName(), activity);
        }
    }
}
