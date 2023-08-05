package lk.ijse.gdse.pos.dao.custom;

import lk.ijse.gdse.pos.entity.Customer;
import lk.ijse.gdse.pos.dao.CrudDao;

public interface CustomerDao extends CrudDao<Customer,String> {

   // public Optional<Customer> search(String id) throws SQLException, ClassNotFoundException;
}
