package spring.learning.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// step 1: create controller class
// controller annotation inherits from @component and support component scan
@Controller
public class HomeController {
    // step 2: define controller method
    // step 3: add the request mapping to controller method
    @RequestMapping(
            value = "/",
            method= RequestMethod.GET)
    public String displayMainPage() {
        return "main";
    }
}
