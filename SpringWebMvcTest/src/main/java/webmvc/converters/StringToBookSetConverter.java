package webmvc.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import webmvc.models.Book;
import webmvc.services.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StringToBookSetConverter implements Converter<String[], Set<Book>> {

    @Autowired
    private BookService bookService;

    @Override
    public Set<Book> convert(String[] s) {
        List<Long> bookId  = Arrays.stream(s)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        return bookService.getBooksById(bookId);
    }
}
