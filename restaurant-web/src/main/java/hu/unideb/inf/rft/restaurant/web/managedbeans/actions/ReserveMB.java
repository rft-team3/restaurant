package hu.unideb.inf.rft.restaurant.web.managedbeans.actions;

import hu.unideb.inf.rft.restaurant.client.api.exception.EmailSendingException;
import hu.unideb.inf.rft.restaurant.client.api.service.MailService;
import hu.unideb.inf.rft.restaurant.client.api.service.TableService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.TableVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean(name="reserveBean")
public class ReserveMB {
    @EJB
    private UserService userService;

    @EJB
    private MailService mailService;
    
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

    /*public void addTable(int table) {
        tableService.setTableReservedByNumber(table,true);

        TableVo tableVo = tableService.getTableByNumber(table);

        userService.addTableToUserByName(user.getName(), tableVo);

        reloadUser();
    }*/

    /*public void removeTable(int table) {
        tableService.setTableReservedByNumber(table,false);

        TableVo tableVo = tableService.getTableByNumber(table);

        userService.removeTableFromUserByName(user.getName(), tableVo);

        reloadUser();
    }*/

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

    public void sendReserved(){
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }
        String reservedTables = bundle.getString("email.defpw.dear")+" "+user.getName()+"!<br>";
        reservedTables+=bundle.getString("email.reserve.message");

        for (TableVo table : userTableVoList) {
            reservedTables +=" " + table.getNumber();
        }
        reservedTables+=bundle.getString("email.defpw.endmessage");
        try {
            mailService.sendMail("noreply@restaurant.hu", user.getEmail(), bundle.getString("email.reserve.subject"), reservedTables);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    bundle.getString("reserve.sendMail.success.summary"),
                    bundle.getString("reserve.sendMail.success.detail")));
        } catch (EmailSendingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    bundle.getString("reserve.sendMail.error.summary"),
                    bundle.getString("reserve.sendMail.error.detail")));
        }
    }
}
