package lk.ijse.gdse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail implements SuperEntity {
    private String oId;
    private String itemCode;
    private double unitPrice;
    private int qty;
   // private double total;
}
