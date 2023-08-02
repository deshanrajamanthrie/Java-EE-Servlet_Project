package lk.ijse.gdse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail {
    private String oId;
    private String itemCode;
    private double unitPrice;
    private int qty;
    private double total;
}
