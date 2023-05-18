package uz.najottalim.javan6.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import javax.print.attribute.standard.OrientationRequested;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private String status;
    private Integer customerId;
    public static class OrderRowMapper implements RowMapper<Order>{

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setOrderDate(rs.getDate("order_date").toLocalDate());
            order.setDeliveryDate(rs.getDate("delivery_date").toLocalDate());
            order.setStatus(rs.getString("status"));
            order.setCustomerId(rs.getInt("customer_id"));
            return order;
        }
    }
}
