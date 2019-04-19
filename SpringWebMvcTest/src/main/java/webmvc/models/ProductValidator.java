package webmvc.models;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {

        return Product.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Product product = (Product) o;

        if (product.getName() == null){
            errors.rejectValue("name", "field.min.length",
                    new Object[]{},
                    "empty name");
        }
    }
}
