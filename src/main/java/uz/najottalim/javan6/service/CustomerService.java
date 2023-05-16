package uz.najottalim.javan6.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dao.Customer;

import java.util.*;

@Service
@Data
public class CustomerService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers(){
        String query = "select * from customer";
        List<Customer> result = jdbcTemplate.query(query, new Customer.CustomerRowMapper());
        return result;
    }
}