package hu.unideb.inf.rft.restaurant.web.managedbeans.actions;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import hu.unideb.inf.rft.restaurant.client.api.exception.EmailSendingException;
import hu.unideb.inf.rft.restaurant.client.api.service.MailService;
import hu.unideb.inf.rft.restaurant.client.api.service.ReserveService;
import hu.unideb.inf.rft.restaurant.client.api.service.TableService;
import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.ReserveVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.TableVo;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class ScheduleMB implements Serializable {

    private ScheduleModel eventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    @EJB
    private TableService tableService;
    @EJB
    private ReserveService reserveService;
    @EJB
    private UserService userService;
    @EJB
    private MailService mailService;

    private UserVo user;
    private boolean ownerOfReserve;

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByName(username);

        eventModel = new DefaultScheduleModel();

        for (TableVo tableVo : tableService.getTables()) {
            for (ReserveVo reserveVo: reserveService.getReservesByTableId(tableVo.getId())) {
                eventModel.addEvent(new DefaultScheduleEvent( tableVo.getNumber()+"", reserveVo.getStartTime(), reserveVo.getEndTime() ));
            }
        }
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public boolean isOwnerOfReserve() {
        return ownerOfReserve;
    }

    public void setOwnerOfReserve(boolean ownerOfReserve) {
        this.ownerOfReserve = ownerOfReserve;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    public void ownerOfSelectedReserve(ScheduleEvent currentEvent) {
        ReserveVo currentReserveVo = new ReserveVo();
        TableVo tableVo = tableService.getTableByNumber(Integer.parseInt(currentEvent.getTitle()));
        for (ReserveVo reserveVo : reserveService.getReservesByTableId(tableVo.getId())) {
            if (reserveVo.getStartTime().equals(currentEvent.getStartDate()) &&
                    reserveVo.getEndTime().equals(currentEvent.getEndDate())) {
                currentReserveVo = reserveVo;
                break;
            }
        }

        ownerOfReserve = user.getReserves().contains(currentReserveVo);
    }

    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null) {
            eventModel.addEvent(event);

            ReserveVo reserveVo = new ReserveVo();
            reserveVo.setStartTime(event.getStartDate());
            reserveVo.setEndTime(event.getEndDate());

            reserveService.addReserve(reserveVo);
            reserveVo = reserveService.getReserves().get(reserveService.getReserves().size()-1);
            reserveService.addReserveToTable(reserveVo, Integer.parseInt(event.getTitle()));
            reserveService.addReserveToUser(reserveVo, user.getId());
        }

        event = new DefaultScheduleEvent();

        sendReserved();
    }

    public void deleteEvent(ActionEvent actionEvent) {
        if (event.getId() != null) {
            eventModel.deleteEvent(event);

            Long reserveId = 0L;
            TableVo tableVo = tableService.getTableByNumber(Integer.parseInt(event.getTitle()));
            for (ReserveVo reserveVo : reserveService.getReservesByTableId(tableVo.getId())) {
                if (reserveVo.getStartTime().equals(event.getStartDate()) &&
                        reserveVo.getEndTime().equals(event.getEndDate())) {
                    reserveId = reserveVo.getId();
                    break;
                }
            }

            reserveService.deleteReserveFromUser(reserveId, user.getId());
            reserveService.deleteReserveFromTable(reserveId, Integer.parseInt(event.getTitle()));
            reserveService.deleteReserve(reserveId);
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
        ownerOfSelectedReserve(event);
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
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

        for (ReserveVo userReserveVo : user.getReserves()) {
            for (TableVo tableVo : tableService.getTables()) {
                for (ReserveVo tableReserveVo : tableVo.getReserves()) {
                    if (userReserveVo.getId().equals(tableReserveVo.getId())) {
                        reservedTables += " " + tableVo.getNumber();
                    }
                }
            }
        }
        /*reservedTables += " " + tableVo.getNumber();*/
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
