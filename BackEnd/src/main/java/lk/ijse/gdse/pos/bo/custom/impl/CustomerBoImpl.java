package lk.ijse.gdse.pos.bo.custom.impl;

import lk.ijse.gdse.pos.dao.custom.CustomerDao;
import lk.ijse.gdse.pos.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.gdse.pos.dto.CustomerDTO;

public class CustomerBoImpl {

    public boolean saveCustomer(CustomerDTO dto) {
        CustomerDao customerDao = new CustomerDaoImpl();
        return customerDao.save(dto);

    }
}
