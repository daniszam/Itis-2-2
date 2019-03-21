package webmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webmvc.models.Author;
import webmvc.models.AuthorValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/author")
public class AuthorFormController {

    @RequestMapping(method = RequestMethod.GET)
    public String authorForm(ModelMap modelMap){
        modelMap.put("author", new Author());
        return "signInCard";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postForm(
            RedirectAttributes redirectAttributes,
            @Valid @ModelAttribute("author") Author author,
            BindingResult result,
            ModelMap map
    ) {
        if (result.hasErrors()) {
            return "signInCard";
        } else {
            redirectAttributes.addFlashAttribute("message", "<span style=\"color:green\">Author \"" + author.getName() + "\" has been added successfully</span>");
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AFC#authorCard").build();
        }
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new AuthorValidator());
    }
}
