package uz.najottalim.javan6.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dao.Customer;
import uz.najottalim.javan6.service.CustomerService;

import java.util.*;

@Service
@Data
public class CustomerServiceImp implements CustomerService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Customer> getAllCustomers(){
        String query = "select * from customer";
        List<Customer> result = jdbcTemplate.query(query, new Customer.CustomerRowMapper());
        return result;
    }

    public List<Customer> getCustomersByOrderId(Integer orderId){
        String query = "select * from customer where id in (select customer_id from product_order where id=?)";
        List<Customer> result = jdbcTemplate.query(query,new Customer.CustomerRowMapper(),orderId);
        return result;
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        String query = "select * from customer where name like ?";
        String prepareName = "%"+name+"%";
        List<Customer> result = jdbcTemplate.query(query, new Customer.CustomerRowMapper(), prepareName);
        return result;
    }

    @Override
    public Customer getCustomerWhoMaxOrder() {
        String query = "select * from customer\n" +
                "         where id=(select customer_id\n" +
                "                   from product_order\n" +
                "                   where\n" +
                "                       id = (select po.id sum from product_order po\n" +
                "                                join\n" +
                "                                order_product_relationship opr\n" +
                "                                    on po.id = opr.order_id\n" +
                "                                join product p\n" +
                "                                    on opr.product_id = p.id group by po.id\n" +
                "                                order by sum(p.price) desc limit 1)\n" +
                "                            );";
        Customer customer = jdbcTemplate.queryForObject(query, new Customer.CustomerRowMapper());
        return customer;
    }


}
