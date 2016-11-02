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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator
@ManagedBean(name = "emailValidator")
@RequestScoped
public class EmailValidator implements Validator {
    @EJB
    private UserService userService;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent,
                         Object value) throws ValidatorException {
        String email = value.toString();

        String pattern = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
        Pattern emailPattern = Pattern.compile(pattern);
        Matcher matcher = emailPattern.matcher(email);

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", facesContext.getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if (!matcher.find()) {
            throw new ValidatorException(new FacesMessage(bundle.getString("emailValidator.pattern")));
        }

        UserVo dbUser = userService.getUserByEmail(email);

        if (dbUser != null) {
            throw new ValidatorException(new FacesMessage(bundle.getString("emailValidator.exists")));
        }
    }
}
