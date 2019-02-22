package task3.xml.scopes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

//        PrinterRedImpl printerRed = (PrinterRedImpl)context.getBean("printer");
//        printerRed.print(100, 50);
//
//        printerRed.setPrefix("НОВЫЙ ПРЕФИКС");
//        printerRed.print(100, 50);
//
//        PrinterRedImpl otherBean = (PrinterRedImpl)context.getBean("printer");
//        otherBean.print(100, 60);
    }
}
