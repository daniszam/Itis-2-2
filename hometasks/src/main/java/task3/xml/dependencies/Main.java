package task3.xml.dependencies;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("context.xml");

//        Terminal terminal = context.getBean(Terminal.class);
//
//        terminal.giveMoney(200);
    }
}
