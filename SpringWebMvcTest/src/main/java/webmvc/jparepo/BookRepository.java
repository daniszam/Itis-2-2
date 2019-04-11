package webmvc.jparepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webmvc.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
