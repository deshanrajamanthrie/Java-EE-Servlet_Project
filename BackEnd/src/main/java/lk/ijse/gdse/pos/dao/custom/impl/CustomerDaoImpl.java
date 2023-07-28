package lk.ijse.gdse.pos.dao.custom.impl;

import lk.ijse.gdse.pos.dao.custom.CustomerDao;
import lk.ijse.gdse.pos.entity.Customer;
import lk.ijse.gdse.pos.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
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

    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (resultSet.next()) {
            allCustomers.add(new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4)));
        }
        return allCustomers;
    }


}
