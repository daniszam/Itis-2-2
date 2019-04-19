package webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webmvc.jparepo.ProductRepo;
import webmvc.models.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping
    private String get(ModelMap map){
        List<String>  types = new ArrayList<>();
        types.add("phone");
        types.add("book");
        map.put("types", types);
        map.put("product", new Product());

        return "manufacturer";
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new ProductValidator());
    }

    @PostMapping
    private String post(
            RedirectAttributes redirectAttributes,
            @RequestParam("manufacturer_name") Manufacturer manufacturer,
            @Valid @ModelAttribute("product") Product product,
            BindingResult result,
            ModelMap map
    ){
        if (result.hasErrors()) {
            return "manufacturer";
        } else {
            productRepo.save(product);
        }
        return "manufacturer";
    }

}
