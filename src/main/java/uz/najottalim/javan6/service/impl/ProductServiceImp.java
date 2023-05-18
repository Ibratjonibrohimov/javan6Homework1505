package uz.najottalim.javan6.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dao.Product;
import uz.najottalim.javan6.service.ProductService;

import java.util.Date;
import java.util.List;

@Data
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public List<Product> getProductsByCategory(String categoryName){
        String query = "select * from product where category=?";
        List<Product> result = jdbcTemplate.query(query, new Product.ProductRowMapper(), categoryName);
        return result;
    }

    public List<Product> getAllProductsOrderByPriceAcs(){
        String query = "select * from product order by price";
        List<Product> result = jdbcTemplate.query(query, new Product.ProductRowMapper());
        return result;
    }

    public List<Product> getProductsByOrderDate( Date orderDate ){
        String query = "" +
                "select p.id,p.category,p.name,p.price from order_product_relationship  rel join\n" +
                "(select id,order_date from product_order where order_date = ?) as orderId\n" +
                "    on rel.order_id = orderId.id\n" +
                "join product p on rel.product_id = p.id;";
        List<Product> result = jdbcTemplate.query(query, new Product.ProductRowMapper(),orderDate);
        return result;
    }

    public Double getSumOfALlOrderPrice(){
        String query ="select sum(p.price) " +
                "from " +
                "product p " +
                "join " +
                "order_product_relationship opr " +
                "on p.id = opr.product_id";
        Double result = jdbcTemplate.queryForObject(query, Double.class);
        return result;
    }

    public Double getAvgPriceByOrderDate(Date orderDate){
        String query = "select avg(p.price)\n" +
                "from order_product_relationship  rel\n" +
                "    join\n" +
                "    (select id,order_date from product_order where order_date = ?) as orderId\n" +
                "        on rel.order_id = orderId.id\n" +
                "    join product p on rel.product_id = p.id;";
        Double result = jdbcTemplate.queryForObject(query, Double.class, orderDate);
        return result;
    }

    @Override
    public List<Product> getProductsByOrderId(Integer orderId) {
        String query = "select p.id,p.category,p.name,p.price from product  p join order_product_relationship opr on p.id = opr.product_id\n" +
                "where opr.order_id = ?;";

        List<Product> result = jdbcTemplate.query(query, new Product.ProductRowMapper(), orderId);
        return result;
    }

    @Override
    public Double getAllSumOrderId(Date orderDate) {
        String query = "select sum(price) from product join order_product_relationship opr on product.id = opr.product_id\n" +
                "join product_order po on opr.order_id = po.id where po.order_date = ?";
        Double result = jdbcTemplate.queryForObject(query, Double.class, orderDate);
        return result;
    }

    @Override
    public Product getMaxPriceProductByCategory(String category) {
        String query = "select * from product where category = ? " +
                "and " +
                "price = (select max(price) from product where category = ?);";
        Product product = jdbcTemplate.queryForObject(query, new Object[]{category, category}, new Product.ProductRowMapper());
        return product;
    }

    @Override
    public List<Product> getProductsSortOffsetLimit(int limit, int offset, String columnName) {
        String query = "select * from product order by :columnName offset :offset limit :limit;";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("columnName",columnName);
        mapSqlParameterSource.addValue("offset",offset);
        mapSqlParameterSource.addValue("limit",limit);
        List<Product> result = namedParameterJdbcTemplate.query(query, mapSqlParameterSource, new Product.ProductRowMapper());
        return result;
    }

    @Override
    public List<Product> getProductsByCustomerStatus(Integer customerId, String status) {
        String query = "select p.id,p.category,p.name,p.price from product_order po\n" +
                "    join order_product_relationship opr\n" +
                "        on po.id = opr.order_id\n" +
                "    join product p\n" +
                "        on p.id = opr.product_id\n" +
                "where po.customer_id = ? and po.status= ?";
        List<Product> result = jdbcTemplate.query(query, new Product.ProductRowMapper(), customerId, status);
        return result;
    }

}
