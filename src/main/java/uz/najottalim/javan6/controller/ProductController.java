package uz.najottalim.javan6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.najottalim.javan6.dao.Product;
import uz.najottalim.javan6.service.ProductService;

import javax.print.attribute.standard.PrinterURI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName){
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("/all")
    public List<Product> getAllProductsOrderByPriceAsc(){
        return productService.getAllProductsOrderByPriceAcs();
    }

    @GetMapping("/order-date/{orderDate}")
    public List<Product> getProductsBuOrderDate(@PathVariable("orderDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return productService.getProductsByOrderDate(date);
    }
    @GetMapping("/orders/all-sum")
    public double getSumOfAllOrderPrice(){
        return productService.getSumOfALlOrderPrice();
    }

    @GetMapping("/order/avg/{date}")
    public double getSumOfAllOrderPrice(@PathVariable("date")@DateTimeFormat(pattern = "yyyy-MM-dd")Date date){
        return productService.getAvgPriceByOrderDate(date);
    }

    @GetMapping("/orders/{orderId}")
    public List<Product> getProductsByOrderId(@PathVariable Integer orderId){
        return productService.getProductsByOrderId(orderId);
    }

    @GetMapping("/orders/all-sum/{orderDate}")
    public Double getAllSumByOrderId(@PathVariable("orderDate")@DateTimeFormat(pattern = "dd-MM-yyyy") Date orderDate){
        return productService.getAllSumOrderId(orderDate);

    }

    @GetMapping("/{category}/max")
    public Product getMaxPriceProductByCategory(@PathVariable String category){
        return productService.getMaxPriceProductByCategory(category);
    }

    @GetMapping("/all/limit/{limit}/offset/{offset}/sortBy/{columnName}")
    public List<Product> getProductsSortOffsetLimit(@PathVariable int limit,@PathVariable int offset,@PathVariable String columnName){
        return productService.getProductsSortOffsetLimit(limit,offset,columnName);
    }

    @GetMapping("/orders/{status}/customer/{customerId}")
    public List<Product> getProductsByCustomerStatus(@PathVariable Integer customerId,@PathVariable String status){
        return productService.getProductsByCustomerStatus(customerId,status);
    }
}
