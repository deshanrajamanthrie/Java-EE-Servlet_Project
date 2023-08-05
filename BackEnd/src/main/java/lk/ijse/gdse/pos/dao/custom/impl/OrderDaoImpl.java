package lk.ijse.gdse.pos.dao.custom.impl;

import lk.ijse.gdse.pos.dao.custom.OrderDao;
import lk.ijse.gdse.pos.entity.Orders;
import lk.ijse.gdse.pos.bo.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    public boolean save(Orders order) throws SQLException, ClassNotFoundException {
        boolean b = SqlUtil.executeUpdate("INSERT INTO Orders VALUES (?,?,?)", order.getOId()
                , order.getCustomerId(), order.getDate());
        return b;
    }

    public boolean update(Orders order) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE Orders SET customerId=?,date=? WHERE oid=?", order.
                getCustomerId(), order.getDate(), order.getOId());
    }

    public boolean delete(String oid) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("DELETE Orders WHERE oid=?", oid);
    }

    public List<Orders> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.executeQuery("SELECT *FROM orders");
        ArrayList<Orders> allOders = new ArrayList<>();
        while (resultSet.next()) {
            allOders.add(new Orders(resultSet.getString(1), resultSet.getString(2),
                    resultSet.getDate(3)));
        }
        return allOders;
    }

    public ResultSet search(String id) throws SQLException, ClassNotFoundException {
         return SqlUtil.executeQuery("SELECT * FROM Orders WHERE oid=?",id);
    }
}