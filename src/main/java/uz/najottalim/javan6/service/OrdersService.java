package uz.najottalim.javan6.service;

import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dao.Order;
import java.util.*;

@Service
public interface OrdersService {

    List<Order> getOrderByCustomerId(Integer customerId);
}
