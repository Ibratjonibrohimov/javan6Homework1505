package uz.najottalim.javan6.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Integer id;
    private String category;
    private Double price;
    private String name;

    public static class ProductRowMapper implements RowMapper<Product>{
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.id=rs.getInt("id");
            product.name=rs.getString("name");
            product.price=rs.getDouble("price");
            product.category=rs.getString("category");
            return product;
        }
    }

}
