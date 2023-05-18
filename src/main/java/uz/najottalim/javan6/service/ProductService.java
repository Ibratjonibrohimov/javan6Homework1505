package uz.najottalim.javan6.service;

import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dao.Product;

import java.util.Date;
import java.util.List;
@Service
public interface ProductService {
    List<Product> getProductsByCategory(String categoryName);
    List<Product> getAllProductsOrderByPriceAcs();
    List<Product> getProductsByOrderDate( Date orderDate );
    Double getSumOfALlOrderPrice();
    Double getAvgPriceByOrderDate(Date orderDate);
    List<Product> getProductsByOrderId(Integer OrderId);

    Double getAllSumOrderId(Date orderDate);

    Product getMaxPriceProductByCategory(String category);

    List<Product> getProductsSortOffsetLimit(int limit, int offset, String columnName);

    List<Product> getProductsByCustomerStatus(Integer customerId, String status);
}

