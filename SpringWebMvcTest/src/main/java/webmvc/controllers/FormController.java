package webmvc.controllers;

import org.springframework.stereotype.Service;

import javax.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webmvc.models.Gender;
import webmvc.models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequestMapping(value = "/form")
public class FormController {

    @RequestMapping(method = RequestMethod.POST)
    public String postForm(
            RedirectAttributes redirectAttributes,
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            ModelMap map
    ) {
        if (result.hasErrors()) {
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

    @RequestMapping(method = RequestMethod.GET)
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
        return "form";
    }
}
