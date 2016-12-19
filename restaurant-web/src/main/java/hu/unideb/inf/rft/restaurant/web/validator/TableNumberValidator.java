package hu.unideb.inf.rft.restaurant.web.validator;

import hu.unideb.inf.rft.restaurant.client.api.service.TableService;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator
@ManagedBean(name = "tableNumberValidator")
@RequestScoped
public class TableNumberValidator implements Validator {

    @EJB
    private TableService tableService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object value) throws ValidatorException {
        int tableNumber = Integer.parseInt(value.toString());

        int tableCount = tableService.getTables().size();

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", facesContext.getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if ((tableNumber < 1) || (tableNumber > tableCount)) {
            throw new ValidatorException(new FacesMessage(bundle.getString("tableNumberValidator.error")));
        }
    }
}
