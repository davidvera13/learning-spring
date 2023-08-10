package spring.learning.springmvc.controller;

import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.learning.springmvc.domain.Customer;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    // fix string with only spaces ...
    // remove leading and trailing whitespaces to strings
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(
            value = "/show-form")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @RequestMapping(
            value = "/handle-form")
    public String handleForm(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult) {
        // | allow to check white space...
        System.out.println("lastName => " + customer.getLastName() +"|");
        // inspect binding result
        // bindingResult => org.springframework.validation.BeanPropertyBindingResult: 1 errors
        // Field error in object 'customer' on field 'freePasses': rejected value [asc];
        // codes [typeMismatch.customer.freePasses,typeMismatch.freePasses,typeMismatch.java.lang.Integer,typeMismatch];
        // arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [customer.freePasses,freePasses];
        // arguments []; default message [freePasses]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'java.lang.Integer' for property 'freePasses';
        // For input string: "abc"]
        System.out.println("bindingResult => " + bindingResult);
        System.out.println("\n\n\n\n\n");
        if(bindingResult.hasErrors())
            return "customer-form";

        return "handle-form";
    }

}
