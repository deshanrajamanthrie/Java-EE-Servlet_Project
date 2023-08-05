package lk.ijse.gdse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements SuperEntity{
    private String oId;
    private String customerId;
    private Date date;
}
