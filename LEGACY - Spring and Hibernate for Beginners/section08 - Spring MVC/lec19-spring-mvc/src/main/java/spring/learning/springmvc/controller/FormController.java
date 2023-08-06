package spring.learning.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {
    @RequestMapping(
            value = "/show-form",
            method= RequestMethod.GET)
    public String showForm() {
        return "01forms/show-form";
    }

    @RequestMapping(
            value = "/handle-form",
            method= RequestMethod.GET)
    public String handleForm() {
        return "01forms/handle-form";
    }

}
