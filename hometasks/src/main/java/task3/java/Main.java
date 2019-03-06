package task3.java;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task3.components.Converter;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(JavaConfig.class);

        Converter converter = context.getBean("converter", Converter.class);
        System.out.println(converter.convert(Converter.Currency.USD, Converter.Currency.RUB, 50));

        ApplicationContext contextXml =
                new ClassPathXmlApplicationContext("context.xml");
        Converter converterXml =(Converter) contextXml.getBean("converter");
        System.out.println(converterXml.convert(Converter.Currency.USD, Converter.Currency.RUB, 50));
        converterXml.print_currency();

        ApplicationContext contextComponentScan = new
                AnnotationConfigApplicationContext(AppConfig.class);
        Converter converterComponentScan =(Converter) contextComponentScan.getBean("converter");
        System.out.println(converterComponentScan.convert_with_request(Converter.Currency.USD, Converter.Currency.RUB, 50));
    }
}
