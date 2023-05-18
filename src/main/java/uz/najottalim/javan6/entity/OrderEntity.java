package uz.najottalim.javan6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import uz.najottalim.javan6.dao.Product;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate order_date;
    @Column(nullable = false)
    private LocalDate delivery_date;
    @Column(nullable = false)
    private String status;

    @Column(name = "customer_id" , nullable = false)
    private Long customerId;
}
