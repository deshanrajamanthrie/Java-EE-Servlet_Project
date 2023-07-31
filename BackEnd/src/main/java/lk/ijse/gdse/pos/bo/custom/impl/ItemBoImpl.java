package lk.ijse.gdse.pos.bo.custom.impl;

import lk.ijse.gdse.pos.bo.custom.ItemBo;
import lk.ijse.gdse.pos.dao.custom.ItemDao;
import lk.ijse.gdse.pos.dao.custom.impl.ItemDaoImpl;
import lk.ijse.gdse.pos.dto.CustomerDTO;
import lk.ijse.gdse.pos.dto.ItemDTO;
import lk.ijse.gdse.pos.entity.Item;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    ItemDao itemDao = new ItemDaoImpl();
    ModelMapper modelMapper = new ModelMapper();

    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDao.save(modelMapper.map(itemDTO, Item.class));
    }

    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDao.update(modelMapper.map(itemDTO, Item.class));
    }

    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDao.delete(code);
    }

    public ResultSet searchItem(String code) throws SQLException, ClassNotFoundException {
        return itemDao.search(code);
    }

    public List<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return modelMapper.map(itemDao.getAll(), new TypeToken<List<ItemDTO>>() {
        }.getType());
    }
}
