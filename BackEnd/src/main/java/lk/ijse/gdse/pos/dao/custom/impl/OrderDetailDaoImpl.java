package lk.ijse.gdse.pos.dao.custom.impl;

import lk.ijse.gdse.pos.dao.custom.OrderDetailDao;
import lk.ijse.gdse.pos.entity.OrderDetail;
import lk.ijse.gdse.pos.bo.util.SqlUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl implements OrderDetailDao {
    public boolean save(OrderDetail od) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("INSERT INTO OrderDetail VALUES(?,?,?,?)",
                od.getOId(), od.getItemCode(), od.getUnitPrice(), od.getQty());

    }

    public boolean update(OrderDetail od) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeUpdate("UPDATE OrderDetail SET itemCode=?,unitPrice=?,qty=? WHERE oid=?",
                od.getItemCode(), od.getUnitPrice(), od.getQty(), od.getOId());
    }

    public boolean delete(String oid) throws SQLException, ClassNotFoundException {
        boolean b = SqlUtil.executeUpdate("DELETE OrderDetail WHERE oid=?", oid);
        return b;
    }

    public List<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.executeQuery("SELECT * FROM Orderdetail");
        ArrayList<OrderDetail> allOD = new ArrayList<>();
        while (rst.next()) {
            allOD.add(new OrderDetail(rst.getString(1), rst.getString(2),
                    rst.getDouble(3), rst.getInt(4)));
        }
        return allOD;
    }

    public ResultSet search(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.executeQuery("SELECT OrderDetail WHERE oid=?", id);


    }


}
