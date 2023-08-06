package spring.learning.springmvc.controller;

//import javax.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormModelController {
    @RequestMapping(
            value = "/show-form-model",
            method= RequestMethod.GET)
    public String showForm() {
        return "02forms-model/show-form";
    }

    @RequestMapping(
            value = "/handle-form-model",
            method= RequestMethod.GET)
    public String handleForm(HttpServletRequest request, Model model) {
        // read request parameter from html form
        String name = request.getParameter("studentName");
        // update value to uppercase and update response
        String output = "Ola " + name.toUpperCase();
        model.addAttribute("output", output);
        return "02forms-model/handle-form";
    }

}
