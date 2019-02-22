package task3.java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import task3.components.*;

@Configuration
public class JavaConfig {

//    @Value("${printer.name}")
//    private String printerName;
//
//    @Value("${printer.prefix}")
//    private String printerPrefix;
//
//    @Value("${terminal.amount}")
//    private Double terminalAmount;

    @Bean
    public Converter converter() {
        return new Converter();
    }

}
