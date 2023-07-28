package lk.ijse.gdse.pos.util;

import lk.ijse.gdse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CrudDao extends SuperDao {
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException;


    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException;

    public ResultSet search(String id) throws SQLException, ClassNotFoundException;

    public ResultSet getAll() throws SQLException, ClassNotFoundException;
}
