package webmvc.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import webmvc.models.User;
import webmvc.models.UserDetailsImpl;
import webmvc.models.UserJpa;

@Controller
//@RequestMapping(value = "/freemarker")
public class FreemarkerController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(@AuthenticationPrincipal UserDetailsImpl userDetails, ModelMap modelMap){
        System.out.println(userDetails);
//        User user  = new User();
//        user.setUserName("danis");
//        modelMap.put("user", user);
        return "home";
    }

    @RequestMapping(value = "/ftl")
    public String ftl(ModelMap modelMap){
        modelMap.put("user", new User());
        return "signInCard";
    }
}
