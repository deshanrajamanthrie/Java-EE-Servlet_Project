package lk.ijse.gdse.pos.bo.custom.impl;

import lk.ijse.gdse.pos.bo.BOTypes;
import lk.ijse.gdse.pos.bo.custom.CustomerBo;
import lk.ijse.gdse.pos.dao.SuperDao;
import lk.ijse.gdse.pos.dao.custom.CustomerDao;
import lk.ijse.gdse.pos.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.gdse.pos.dao.util.DAOFactory;
import lk.ijse.gdse.pos.dao.util.DAOTypes;
import lk.ijse.gdse.pos.dto.CustomerDTO;
import lk.ijse.gdse.pos.entity.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    ModelMapper modelMapper = new ModelMapper();
    CustomerDao customerDao = (CustomerDao) DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);


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
        return modelMapper.map(customerDao.getAll(), new TypeToken<List<CustomerDTO>>() {
        }.getType());

    }

    public ResultSet searchCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDao.search(id);
    }
}
