package lk.ijse.gdse.pos.bo.custom;

import lk.ijse.gdse.pos.bo.SuperBo;
import lk.ijse.gdse.pos.dto.OrderDTO;

import java.sql.SQLException;

public interface PurchaseOrderBO extends SuperBo {
    boolean saveOrder(OrderDTO orderDTO) throws SQLException;
}
