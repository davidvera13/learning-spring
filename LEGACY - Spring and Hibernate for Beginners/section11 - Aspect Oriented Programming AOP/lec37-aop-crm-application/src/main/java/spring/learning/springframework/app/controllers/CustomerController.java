package spring.learning.springframework.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.learning.springframework.app.entity.Customer;
import spring.learning.springframework.app.helpers.SortUtils;
import spring.learning.springframework.app.services.CustomerService;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController  {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list")
    public String listCustomer(
            Model model,
            @RequestParam(required=false) String sort) {
        // List<Customer> customers = customerDao.getCustomers();
        // get customers from the service
        List<Customer> theCustomers = null;

        // check for sort field
        if (sort != null) {
            int sortField = Integer.parseInt(sort);
            theCustomers = customerService.getCustomers(sortField);
        }
        else {
            // no sort field provided ... default to sorting by last name
            theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
        }

        // add the customers to the model
        model.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @GetMapping("showFormForAdd")
    public String showFormForAdd(Model model) {
        Customer customer =  new Customer();
        model.addAttribute("customer", customer);
        return "customer-form";
    }
    @GetMapping("showFormForUpdate")
    public String showFormForAdd(@RequestParam("customerId") long customerId, Model model) {
        // get the customer from database
        Customer customer = customerService.getCustomer(customerId);
        // set customer as model attribute to prepopulate form
        model.addAttribute("customer", customer);
        // send over to form
        return "customer-form";
    }

    // save update
    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        // save the customer using service
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("searchedName") String searchedName,
                                  Model theModel) {
        // search customers from the service
        List<Customer> customers = customerService.searchCustomers(searchedName);
        // add the customers to the model
        theModel.addAttribute("customers", customers);
        return "list-customers";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") long customerId ) {
        customerService.deleteCustomer(customerId);
        return "redirect:/customer/list";
    }
}
