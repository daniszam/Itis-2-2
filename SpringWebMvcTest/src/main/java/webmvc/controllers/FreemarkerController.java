package webmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import webmvc.models.User;

@Controller
@RequestMapping(value = "/freemarker")
public class FreemarkerController {

    @RequestMapping(value = "/home")
    public String home(ModelMap modelMap){
        User user  = new User();
        user.setUserName("danis");
        modelMap.put("user", user);
        return "home";
    }

    @RequestMapping(value = "/ftl")
    public String ftl(ModelMap modelMap){
        modelMap.put("user", new User());
        return "signInCard";
    }
}
