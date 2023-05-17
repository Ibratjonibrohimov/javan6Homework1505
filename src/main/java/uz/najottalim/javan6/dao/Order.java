package uz.najottalim.javan6.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
