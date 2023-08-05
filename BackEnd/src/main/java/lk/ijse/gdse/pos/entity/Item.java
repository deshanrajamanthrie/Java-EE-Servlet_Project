package lk.ijse.gdse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item implements SuperEntity{
    private String code;
    private String itemName;
    private int qty;
    private double unitPrice;


}
