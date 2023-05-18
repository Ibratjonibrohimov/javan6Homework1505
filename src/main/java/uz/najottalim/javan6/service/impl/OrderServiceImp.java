package uz.najottalim.javan6.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import uz.najottalim.javan6.dao.Customer;
import uz.najottalim.javan6.dao.Order;
import uz.najottalim.javan6.service.OrdersService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImp implements OrdersService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Order> getOrderByCustomerId(Integer customerId) {
        String query = "select * from product_order where customer_id=?";
        List<Order> result = jdbcTemplate.query(query,
                new ResultSetExtractor<List<Order>>() {

                    @Override
                    public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<Order> result = new ArrayList<>();
                        while (rs.next()) {
                            Order order = new Order();
                            order.setId(rs.getInt("id"));
                            order.setOrderDate(rs.getDate("order_date").toLocalDate());
                            order.setDeliveryDate(rs.getDate("delivery_date").toLocalDate());
                            order.setStatus(rs.getString("status"));
                            order.setCustomerId(rs.getInt("customer_id"));
                            result.add(order);
                        }
                        return result;
                    }
                }, customerId);

        return result;
    }

    @Override
    public List<Order> getMostrecentOrders(Integer count) {
        String query = "select * from product_order order by order_date desc limit ?";
        List<Order> result = jdbcTemplate.query(query, new Order.OrderRowMapper(), count);
        return result;
    }

    @Override
    public List<Order> getOrdersLastCountDay(Integer count) {
        String query = "select * from product_order\n" +
                "         where order_date\n" +
                "             between\n" +
                "             (select max(order_date) from product_order) - ?\n" +
                "             and (select max(order_date) from product_order) ;";
        List<Order> result = jdbcTemplate.query(query, new Order.OrderRowMapper(), count);
        return result;
    }

    @Override
    public Double getAllPriceByStatus(String status) {
        String query = "select sum(p.price) from product_order po\n" +
                "    join order_product_relationship opr\n" +
                "        on po.id = opr.order_id\n" +
                "    join product p\n" +
                "        on p.id = opr.product_id\n" +
                "where po.status= ?";
        Double result = jdbcTemplate.queryForObject(query, Double.class, status);
        return result;
    }
}
