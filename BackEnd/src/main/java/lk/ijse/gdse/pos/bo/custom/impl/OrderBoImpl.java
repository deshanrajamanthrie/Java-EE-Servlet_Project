package lk.ijse.gdse.pos.bo.custom.impl;

import lk.ijse.gdse.pos.dao.custom.OrderDao;
import lk.ijse.gdse.pos.dao.custom.impl.OrderDaoImpl;
import lk.ijse.gdse.pos.dto.OrderDTO;
import lk.ijse.gdse.pos.entity.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderBoImpl {
    OrderDao orderDao = new OrderDaoImpl();
    ModelMapper modelMapper = new ModelMapper();


    public boolean saveOrders(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDao.save(modelMapper.map(orderDTO, Order.class));
    }

    public boolean updateOrders(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return orderDao.update(modelMapper.map(orderDTO, Order.class));
    }

    public boolean deleteOrders(String oid) throws SQLException, ClassNotFoundException {
        return orderDao.delete(oid);
    }

    public List<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        return modelMapper.map(orderDao.getAll(), new TypeToken<List<Order>>() {
        }.getType());
    }

    public ResultSet searchOrders(String oid) throws SQLException, ClassNotFoundException {
        return orderDao.search(oid);
    }


}
