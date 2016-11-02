package hu.unideb.inf.rft.restaurant.web.managedbeans.admin;

import hu.unideb.inf.rft.restaurant.client.api.service.RoleService;
import hu.unideb.inf.rft.restaurant.client.api.vo.RoleVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "roleManagement")
public class RoleManagementMB {

    @EJB
    private RoleService roleService;

    private List<RoleVo> userRoles = new ArrayList<RoleVo>();
    private String userRole;

    @PostConstruct
    public void init() {
        userRoles.addAll(roleService.getRoles());
    }

    public List<RoleVo> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleVo> userRoles) {
        this.userRoles = userRoles;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}
