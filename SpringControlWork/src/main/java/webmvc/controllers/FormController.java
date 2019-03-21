package webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webmvc.models.Gender;
import webmvc.models.User;
import webmvc.models.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping(value = "/form")
public class FormController {


    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    private MessageSourceAccessor msa;

    @Autowired
    @Qualifier("messageSource")
    private void setMessageSourceAccessor(MessageSource ms){
        this.msa = new MessageSourceAccessor(ms);
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postForm(
            RedirectAttributes redirectAttributes,
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            ModelMap map
    ) {
        if (result.hasErrors()) {
            Map<String, String> countryItems = new LinkedHashMap<>();
            countryItems.put("US", "United States");
            countryItems.put("IT", "Italy");
            countryItems.put("UK", "United Kingdom");
            countryItems.put("FR", "France");
            map.put("countryItems", countryItems);
            map.put("male", Gender.MALE);
            map.put("female", Gender.FEMALE);

            map.put("email", this.msa.getMessage("email"));
            map.put("password", this.msa.getMessage("password"));
            return "form";
        } else {
            redirectAttributes.addFlashAttribute("message", "<span style=\"color:green\">USER \"" + user.getUserName() + "\" has been added successfully</span>");
            try(FileWriter writer = new FileWriter("webmvc.txt", false))
            {
                String text = user.toString();
                writer.write(text);
                writer.append('\n');
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("FC#form").build();
        }
    }

    @RequestMapping (method = RequestMethod.GET)
    public String form(ModelMap modelMap){
        Map<String, String> countryItems = new LinkedHashMap<>();
        countryItems.put("US", "United States");
        countryItems.put("IT", "Italy");
        countryItems.put("UK", "United Kingdom");
        countryItems.put("FR", "France");
        modelMap.put("countryItems", countryItems);
        modelMap.put("user", new User());
        modelMap.put("male", Gender.MALE);
        modelMap.put("female", Gender.FEMALE);

//        modelMap.put("email", this.msa.getMessage("email"));
//        modelMap.put("password", this.msa.getMessage("password"));

        modelMap.put("email", "email");
        modelMap.put("password", "pass");
        return "form";
    }

//    @RequestMapping("/change")
//    public String change(@RequestParam String locale){
//        // Check here for param format
////        String[] localeData = locale.split("_");
////        localeResolver.setLocale(request,  response, new Locale(localeData[0], localeData[1]));
////        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("FC#form").build();
//    }



}
