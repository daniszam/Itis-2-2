package webmvc.services;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractResourceBasedMessageSource;
import org.springframework.stereotype.Service;
import webmvc.repository.LocaleRepo;

import java.text.MessageFormat;
import java.util.Locale;

@Service
@NoArgsConstructor
public class MyMessageSource extends AbstractResourceBasedMessageSource {

//    @Autowired
//    private LocaleRepo localeRepo;

    @Override
    protected MessageFormat resolveCode(String s, Locale locale) {
        String db = locale.getLanguage();
//        String result = this.localeRepo.getText(db, s);
        String result = "sdf";
        if (result==null){
            return null;
        }
        return new MessageFormat(result, locale);
    }
}
