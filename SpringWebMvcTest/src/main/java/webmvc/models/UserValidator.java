package webmvc.models;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {

    private static final int MINIMUM_PASSWORD_LENGTH = 6;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "empty" );

        User user = (User) o;

        if (user.getPassword() != null && user.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH){
            errors.rejectValue("password", "field.min.length",
                    new Object[]{MINIMUM_PASSWORD_LENGTH},
                    "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
        }
    }
}
