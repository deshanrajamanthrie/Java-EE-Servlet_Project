package lk.ijse.gdse.pos.bo.custom.impl;

import lk.ijse.gdse.pos.dao.custom.CustomerDao;
import lk.ijse.gdse.pos.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.gdse.pos.dto.CustomerDTO;
import lk.ijse.gdse.pos.entity.Customer;
import org.modelmapper.ModelMapper;

import java.sql.SQLException;

public class CustomerBoImpl {
    CustomerDao customerDao = new CustomerDaoImpl();
    ModelMapper modelMapper = new ModelMapper();


    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        boolean save = customerDao.save(modelMapper.map(customerDTO, Customer.class));
        return save;
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDao.update(modelMapper.map(customerDTO,Customer.class));
    }
}
