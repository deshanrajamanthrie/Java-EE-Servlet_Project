package lk.ijse.gdse.pos.dao;

import lk.ijse.gdse.pos.entity.SuperEntity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T extends SuperEntity, ID extends Serializable> extends SuperDao {
    boolean save(T entity) throws SQLException, ClassNotFoundException;

    boolean delete(ID id) throws SQLException, ClassNotFoundException;

    boolean update(T entity) throws SQLException, ClassNotFoundException;

    ResultSet search(ID id) throws SQLException, ClassNotFoundException;

    List<T> getAll() throws SQLException, ClassNotFoundException;
}
