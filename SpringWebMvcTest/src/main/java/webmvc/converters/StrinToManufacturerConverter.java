package webmvc.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import webmvc.jparepo.ManufacturerRepo;
import webmvc.models.Manufacturer;

@Component
public class StrinToManufacturerConverter implements Converter<String, Manufacturer> {

    @Autowired
    private ManufacturerRepo manufacturerRepo;

    @Override
    public Manufacturer convert(String s) {
        Manufacturer manufacturer = manufacturerRepo.findByName(s);
        if (manufacturer == null){
            Manufacturer manufacturerNew = Manufacturer.builder().name(s).build();
            manufacturerRepo.save(manufacturerNew);
            return manufacturerNew;
        }

        return manufacturer;
    }
}
