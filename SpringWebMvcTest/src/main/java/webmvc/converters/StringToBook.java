package webmvc.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import webmvc.jparepo.BookRepository;
import webmvc.models.Book;

@Component
public class StringToBook implements Converter<String, Book> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book convert(String s) {
        return bookRepository.findById(Long.parseLong(s)).get();
    }
}
