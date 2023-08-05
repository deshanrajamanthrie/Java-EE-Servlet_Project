package lk.ijse.gdse.pos.dao.custom.impl;

import lk.ijse.gdse.pos.dao.custom.ItemDao;
import lk.ijse.gdse.pos.entity.Item;
import lk.ijse.gdse.pos.bo.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?)", entity.getCode(), entity.getItemName(), entity.getQty(), entity.getUnitPrice());
    }

    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE Item SET ItemName=?,qty=?,unitPrice=? WHERE code=?", entity.getItemName(), entity.getQty(), entity.getUnitPrice(), entity.getCode());

    }

    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("DELETE FROM Item WHERE code=? ", code);

    }

    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItem = new ArrayList<>();
        while (rst.next()) {
            allItem.add(new Item(rst.getString(1), rst.getString(2), rst.getInt(3),
                    rst.getDouble(4)));
        }
        return allItem;
    }

    public ResultSet search(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT *FROM Item WHERE code=?", code);
        return resultSet;
    }
}
