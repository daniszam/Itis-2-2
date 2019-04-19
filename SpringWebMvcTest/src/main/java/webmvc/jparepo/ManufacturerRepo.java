package webmvc.jparepo;

import org.springframework.data.repository.CrudRepository;
import webmvc.models.Manufacturer;
import webmvc.models.UserJpa;

public interface ManufacturerRepo extends CrudRepository<Manufacturer, Integer> {

    Manufacturer findByName(String name);
}
