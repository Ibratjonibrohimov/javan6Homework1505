package uz.najottalim.javan6.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.najottalim.javan6.dao.Customer;
import uz.najottalim.javan6.service.CustomerService;
import uz.najottalim.javan6.service.impl.CustomerServiceImp;
import java.util.*;
@Data
@RestController

public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/costumer/all")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @GetMapping("/oredrs/costumers/{orderId}")
    public List<Customer> getCostumersByOrderId(@PathVariable Integer orderId){
        return customerService.getCustomersByOrderId(orderId);
    }

    @GetMapping("/customers/{name}")
    public List<Customer> getCustomersByName(@PathVariable String name){
        return customerService.getCustomersByName(name);
    }
    @GetMapping("/customers/orders/max-price")
    public Customer getCustomerWhoMaxOrder(){
        return customerService.getCustomerWhoMaxOrder();
    }
}
