package webmvc.jparepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webmvc.models.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
