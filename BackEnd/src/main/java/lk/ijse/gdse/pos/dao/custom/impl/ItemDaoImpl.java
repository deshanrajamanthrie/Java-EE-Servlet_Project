package lk.ijse.gdse.pos.dao.custom.impl;

import lk.ijse.gdse.pos.entity.Item;
import lk.ijse.gdse.pos.util.SqlUtil;

import java.sql.SQLException;

public class ItemDaoImpl {
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("INSERT INTO Item VALUES(?,?,?,?)", entity.getCode(), entity.getItemName(), entity.getQty(), entity.getUnitPrice());
    }

    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE Item SET ItemName=?,qty=?,unitPrice=? WHERE code=?", entity.getItemName(), entity.getQty(), entity.getUnitPrice(), entity.getCode());

    }
}
