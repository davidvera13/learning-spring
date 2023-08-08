package spring.learning.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// 	Caused by: java.lang.IllegalStateException: Ambiguous mapping.
// 	Cannot map 'formAmbiguousMappingController' method
//     spring.learning.springmvc.controller.FormAmbiguousMappingController#showForm()
//     to {GET [/show-form]}: There is already 'formController' bean method
@Controller
@RequestMapping("/solving/ambiguous/mapping")
public class FormAmbiguousMappingController {
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
