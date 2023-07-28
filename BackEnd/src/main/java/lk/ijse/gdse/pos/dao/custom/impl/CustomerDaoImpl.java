package lk.ijse.gdse.pos.dao.custom.impl;

import lk.ijse.gdse.pos.entity.Customer;
import lk.ijse.gdse.pos.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl {
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("INSERT INTO Customer VALUES(?,?,?,?)",
                entity.getId(), entity.getName(), entity.getAddress(), entity.getContact());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("DELETE FROM Customer WHERE id =?", id);
    }

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE Customer SET name=?,address=?,contact=? WHERE id=?", entity.getName(),
                entity.getAddress(), entity.getContact(), entity.getId());
    }

    public ResultSet search(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeQuery("SELECT * FROM Customer WHERE CustID=?", id);
    }

    public ResultSet getAll() throws SQLException, ClassNotFoundException {
        return SqlUtil.executeQuery("SELECT * FROM Customer");
    }


}
