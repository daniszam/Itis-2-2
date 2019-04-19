package webmvc.jparepo;

import org.springframework.data.repository.CrudRepository;
import webmvc.models.Product;

public interface ProductRepo extends CrudRepository<Product, Integer> {
}
