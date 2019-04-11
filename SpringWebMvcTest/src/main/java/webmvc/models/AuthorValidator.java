package webmvc.models;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.imageio.ImageIO;

@Component
public class AuthorValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return Author.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        ValidationUtils.rejectIfEmptyOrWhitespace(e, "name", "empty" );
//        Author a = (Author) obj;
//        try {
//            URL url = new URL(a.getAvatarUrl());
//            Image image = ImageIO.read(url);
//        } catch (Exception ex) {
//            e.rejectValue("avatarUrl", "incorrect");
//        }
    }
}