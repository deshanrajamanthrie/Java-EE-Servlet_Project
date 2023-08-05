package lk.ijse.gdse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer implements SuperEntity {
    private String id;
    private String name;
    private String address;
    private String contact;
}
