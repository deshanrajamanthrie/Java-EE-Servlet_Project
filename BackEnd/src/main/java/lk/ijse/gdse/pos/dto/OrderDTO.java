package lk.ijse.gdse.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String oId;
    private String customerId;
    private Date date;

    public OrderDTO(String oId, String customerId, Date date) {
        this.oId = oId;
        this.customerId = customerId;
        this.date = date;
    }

    @ToString.Exclude
    List<OrderDetailDTO> orderDetailDTO;

}
