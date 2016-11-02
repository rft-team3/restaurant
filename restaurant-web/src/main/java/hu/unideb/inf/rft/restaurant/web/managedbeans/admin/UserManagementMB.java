package hu.unideb.inf.rft.restaurant.web.managedbeans.admin;

import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "userManagement")
public class UserManagementMB {

    @EJB
    private UserService userService;

    private DualListModel<UserVo> users;
    private DualListModel<UserVo> usersPass;
    private DualListModel<UserVo> usersActive;

    @PostConstruct
    public void init() {
        List<UserVo> usersSource = new ArrayList<UserVo>();
        List<UserVo> usersTarget = new ArrayList<UserVo>();
        List<UserVo> usersPassSource = new ArrayList<UserVo>();
        List<UserVo> usersPassTarget = new ArrayList<UserVo>();
        List<UserVo> usersActiveSource = new ArrayList<UserVo>();
        List<UserVo> usersActiveTarget = new ArrayList<UserVo>();

        usersSource.addAll(userService.getUsers());
        usersPassSource.addAll(userService.getUsers());
        usersActiveSource.addAll(userService.getUsers());

        users = new DualListModel<UserVo>(usersSource, usersTarget);
        usersPass = new DualListModel<UserVo>(usersPassSource, usersPassTarget);
        usersActive = new DualListModel<UserVo>(usersActiveSource, usersActiveTarget);
    }

    public DualListModel<UserVo> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<UserVo> users) {
        this.users = users;
    }

    public DualListModel<UserVo> getUsersPass() {
        return usersPass;
    }

    public void setUsersPass(DualListModel<UserVo> usersPass) {
        this.usersPass = usersPass;
    }

    public DualListModel<UserVo> getUsersActive() {
        return usersActive;
    }

    public void setUsersActive(DualListModel<UserVo> usersActive) {
        this.usersActive = usersActive;
    }
}
