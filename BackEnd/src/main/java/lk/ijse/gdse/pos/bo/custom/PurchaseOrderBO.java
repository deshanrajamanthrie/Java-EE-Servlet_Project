package lk.ijse.gdse.pos.bo.custom;

import lk.ijse.gdse.pos.bo.SuperBo;
import lk.ijse.gdse.pos.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface PurchaseOrderBO extends SuperBo {
    boolean saveOrder(OrderDTO orderDTO) throws SQLException;
     List<OrderDTO> getAll() throws SQLException, ClassNotFoundException;
}
