package hu.unideb.inf.rft.restaurant.web.validator;

import hu.unideb.inf.rft.restaurant.client.api.service.UserService;

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
@ManagedBean(name = "phoneValidator")
@RequestScoped
public class PhoneValidator implements Validator {
    @EJB
    private UserService userService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object value) throws ValidatorException {
        String phone = value.toString();

        String pattern = "^\\+[0-9]+$";
        Pattern emailPattern = Pattern.compile(pattern);
        Matcher matcher = emailPattern.matcher(phone);

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", facesContext.getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if (!matcher.find()) {
            throw new ValidatorException(new FacesMessage(bundle.getString("phoneValidator.pattern")));
        }
    }
}

