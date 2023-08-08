package lk.ijse.gdse.pos.bo.custom;

import lk.ijse.gdse.pos.bo.SuperBo;
import lk.ijse.gdse.pos.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBO extends SuperBo {
     List<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException;

}
