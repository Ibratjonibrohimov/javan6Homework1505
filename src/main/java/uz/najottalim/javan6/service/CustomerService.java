package uz.najottalim.javan6.service;

import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dao.Customer;

import java.util.List;
@Service
public interface CustomerService {
    List<Customer> getAllCustomers();

    List<Customer> getCustomersByOrderId(Integer orderId);

    List<Customer> getCustomersByName(String name);

    Customer getCustomerWhoMaxOrder();
}
