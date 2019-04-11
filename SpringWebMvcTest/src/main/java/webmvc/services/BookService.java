package webmvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webmvc.models.Book;
import webmvc.jparepo.BookRepository;
import com.google.common.collect.Lists;


import java.util.*;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Set<Book> getBooksById(List<Long> bookId){
        Set<Book> books = new HashSet<>();

        for (Long id: bookId){
            Optional<Book> book = bookRepository.findById(id);
            book.ifPresent(books::add);
        }

        return books;
    }

    public List<Book> getAll(){
        return Lists.newArrayList(bookRepository.findAll());
    }

    public void saveAll(Set<Book> books){
        bookRepository.saveAll(books);
    }
}
