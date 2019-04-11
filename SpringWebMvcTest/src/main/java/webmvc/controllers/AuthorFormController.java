package webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webmvc.jparepo.AuthorRepository;
import webmvc.jparepo.BookRepository;
import webmvc.models.Author;
import webmvc.models.AuthorValidator;
import webmvc.models.Book;
import webmvc.services.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/author")
public class AuthorFormController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String authorForm(ModelMap modelMap){
        modelMap.put("author", new Author());

        List<Book> bookList = bookService.getAll();
        modelMap.put("books", bookList);

        return "authorForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postForm(
            RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            @RequestParam("book_id") Set<Book> books,
            @Valid @ModelAttribute("author") Author author,
            BindingResult result,
            ModelMap map
    ) {
        authorRepository.save(author);
        books.forEach(a-> a.setAuthor(author));
        bookService.saveAll(books);

        if (result.hasErrors()) {
            return "signInCard";
        } else {
            redirectAttributes.addFlashAttribute("message", "<span style=\"color:green\">Author \"" + author.getName() + "\" has been added successfully</span>");
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("AFC#authorForm").build();
        }
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(new AuthorValidator());
    }
}
