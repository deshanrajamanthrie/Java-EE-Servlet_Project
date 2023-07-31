package lk.ijse.gdse.pos.bo.custom;

import lk.ijse.gdse.pos.dto.ItemDTO;
import lk.ijse.gdse.pos.entity.Item;
import org.modelmapper.TypeToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ItemBo extends SuperBo {


     boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;


     boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

     boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

     ResultSet searchItem(String code) throws SQLException, ClassNotFoundException;

     List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
}
