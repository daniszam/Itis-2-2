package webmvc.jparepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import webmvc.models.UserJpa;

@Repository
public interface UserJpaRepository extends CrudRepository<UserJpa, Integer> {

    UserJpa findUserJpaByUserName(String s);
}
