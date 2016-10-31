package hu.unideb.inf.rft.restaurant.web.validator;

import hu.unideb.inf.rft.restaurant.client.api.service.UserService;
import hu.unideb.inf.rft.restaurant.client.api.vo.UserVo;

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

@FacesValidator
@ManagedBean(name = "usernameValidator")
@RequestScoped
public class UsernameValidator implements Validator {

    @EJB
    private UserService userService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object submittedValue) throws ValidatorException {
        String username = submittedValue.toString();
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", context.getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if (username.length() < 3) {
            throw new ValidatorException(new FacesMessage(bundle.getString("usernameValidator.length")));
        }

        UserVo dbUser = userService.getUserByName(username);

        if (dbUser != null) {
            throw new ValidatorException(new FacesMessage(bundle.getString("usernameValidator.exists")));
        }
    }

}

