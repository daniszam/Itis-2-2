package webmvc.services;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService {

    public String getHello(){
        return "Hello from Service";
    }
}
