package spring.learning.springmvc.controller;

//import javax.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormParamBindingController {
    @RequestMapping(
            value = "/show-form-request-params",
            method= RequestMethod.GET)
    public String showForm() {
        return "03forms-request-params/show-form";
    }

    @RequestMapping(
            value = "/handle-form-request-params",
            method= RequestMethod.GET)
    public String handleForm(@RequestParam("studentName") String studentName, Model model) {
        // update value to uppercase and update response
        String output = "Ola amigo ! " + studentName.toUpperCase();
        model.addAttribute("output", output);
        return "03forms-request-params/handle-form";
    }

}
