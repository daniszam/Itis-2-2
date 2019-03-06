package webmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/test")
public class Calculus {

    @RequestMapping(method = RequestMethod.GET)
    public String calc(@RequestParam("a") double a, @RequestParam("b") double b,
                       @RequestParam("command") String command, ModelMap modelMap,
                       HttpServletResponse response){

        System.out.println(command);
        Command operation = Command.getCommand(command);
        String response_str = "{error}";
        if (operation!=null){
            response_str = String.format("{command: %s, b: %f}",operation.get(), operation.proccess(a,b) );
        }
        modelMap.put("result", response_str);
        response.setStatus(200);
        return "test";
    }
}

enum Command {
    MULTIPLICATION("*"),
    DIVISION("%"),
    SUMMATION("plus"),
    SUBTRACTION("-");


    private final String command;

    Command(String command){
        this.command = command;
    }

    public String get() {
        return command;
    }

    public static Command getCommand(String command){
        for (Command operation : Command.values()){
            if (command.equals(operation.command)){
                return operation;
            }
        }
        return null;
    }

    Double proccess(double a, double b){
        switch (this){
            case DIVISION:
                return a/b;
            case SUMMATION:
                return  (a + b);
            case SUBTRACTION:
                return a-b;
            case MULTIPLICATION:
                return a*b;
        }
        return null;
    }
}

