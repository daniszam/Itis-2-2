package webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webmvc.jparepo.UserJpaRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/signIn")
public class SignIn {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping
    public String getSignInPage(HttpServletRequest request, ModelMap modelMap){
        if (request.getParameterMap().containsKey("error")){
            modelMap.addAttribute("error", "error");
        }
        return "signIn";
    }

}
