package spring.learning.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.learning.springmvc.domain.Student;

@Controller
@RequestMapping("/students")
public class StudentController {
    @RequestMapping(
            value = "/show-form")
    public String showForm(Model model) {
        // create a new student
        Student student = new Student();
        // add object to model
        model.addAttribute("student", student);
        return "student-form";
    }

    @RequestMapping(
            value = "/handle-form")
    public String handleForm(@ModelAttribute("student") Student student) {
        return "handle-form";
    }

}
