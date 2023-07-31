package lk.ijse.gdse.pos.bo.custom;

import lk.ijse.gdse.pos.dto.CustomerDTO;
import lk.ijse.gdse.pos.entity.Customer;
import org.modelmapper.TypeToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CustomerBo  extends SuperBo{

    boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

    ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException;
}