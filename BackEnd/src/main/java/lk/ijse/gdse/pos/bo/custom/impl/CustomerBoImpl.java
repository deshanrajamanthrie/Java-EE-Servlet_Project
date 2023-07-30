package lk.ijse.gdse.pos.bo.custom.impl;

import lk.ijse.gdse.pos.dao.custom.CustomerDao;
import lk.ijse.gdse.pos.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.gdse.pos.dto.CustomerDTO;
import lk.ijse.gdse.pos.entity.Customer;
import org.modelmapper.ModelMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerBoImpl {
    CustomerDao customerDao = new CustomerDaoImpl();
    ModelMapper modelMapper = new ModelMapper();


    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDao.save(modelMapper.map(customerDTO, Customer.class));

    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDao.update(modelMapper.map(customerDTO, Customer.class));
    }

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.delete(id);

    }

    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        /// return   customerDao.getAll();
        return null;
    }

    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.search(id);

    }
}
