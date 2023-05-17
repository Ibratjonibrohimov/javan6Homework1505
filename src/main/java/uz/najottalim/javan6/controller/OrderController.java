package uz.najottalim.javan6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uz.najottalim.javan6.dao.Order;
import uz.najottalim.javan6.service.OrdersService;
import java.util.*;

@RestController
public class OrderController {
    @Autowired
    OrdersService ordersService;

    @GetMapping("/customers/{customerId}/orders")
    public List<Order> getOrdersByCustomerId(@PathVariable Integer customerId){
        return ordersService.getOrderByCustomerId(customerId);
    }
}
