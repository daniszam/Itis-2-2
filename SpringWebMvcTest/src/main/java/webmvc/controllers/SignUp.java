package webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webmvc.jparepo.UserJpaRepository;
import webmvc.models.User;
import webmvc.models.UserJpa;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/signUp")
public class SignUp {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping
    public String get(ModelMap modelMap){
        modelMap.put("userJpa", new UserJpa());
        return "registration";
    }

    @PostMapping
    public String postForm(
            RedirectAttributes redirectAttributes,
            @Valid @ModelAttribute("userJpa") UserJpa userJpa,
            BindingResult result,
            ModelMap map
    ) {
        userJpa.setPassword(passwordEncoder.encode(userJpa.getPassword()));
        userJpaRepository.save(userJpa);
        return "redirect:/signIn";
    }
}
