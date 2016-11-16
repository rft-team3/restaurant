package hu.unideb.inf.rft.restaurant.web.managedbeans.actions;

import hu.unideb.inf.rft.restaurant.client.api.service.TableService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.TableVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean(name="reserveBean")
public class ReserveMB {
    @EJB
    private UserService userService;
    
    @EJB
    private TableService tableService;

    private UserVo user;

    private List<TableVo> tableVoList = new ArrayList<>();

    private List<TableVo> userTableVoList  = new ArrayList<>();

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);

        tableVoList.addAll(tableService.getTables());
        userTableVoList = user.getTables();
    }

    private void reloadUser(){

        user = userService.getUserByName(user.getName());
        tableVoList = tableService.getTables();
        userTableVoList = user.getTables();
    }

    public void addTable(int table) {
        tableService.setTableReservedByNumber(table,true);

        TableVo tableVo = tableService.getTableByNumber(table);

        userService.addTableToUserByName(user.getName(), tableVo);

        reloadUser();
    }

    public void removeTable(int table) {
        tableService.setTableReservedByNumber(table,false);

        TableVo tableVo = tableService.getTableByNumber(table);

        userService.removeTableFromUserByName(user.getName(), tableVo);

        reloadUser();
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public List<TableVo> getTableVoList() {
        return tableVoList;
    }

    public void setTableVoList(List<TableVo> tableVoList) {
        this.tableVoList = tableVoList;
    }

    public List<TableVo> getUserTableVoList() {
        return userTableVoList;
    }

    public void setUserTableVoList(List<TableVo> userTableVoList) {
        this.userTableVoList = userTableVoList;
    }
}
