package lk.ijse.gdse.pos.bo.custom.impl;

import lk.ijse.gdse.pos.bo.custom.PurchaseOrderBO;
import lk.ijse.gdse.pos.dao.custom.OrderDao;
import lk.ijse.gdse.pos.dao.custom.OrderDetailDao;
import lk.ijse.gdse.pos.dao.custom.impl.OrderDaoImpl;
import lk.ijse.gdse.pos.dao.custom.impl.OrderDetailDaoImpl;
import lk.ijse.gdse.pos.db.DBConnection;
import lk.ijse.gdse.pos.dto.OrderDTO;
import lk.ijse.gdse.pos.dto.OrderDetailDTO;
import lk.ijse.gdse.pos.entity.OrderDetail;
import lk.ijse.gdse.pos.entity.Orders;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PurchaseOrderBOImpl implements PurchaseOrderBO {
    OrderDao orderDao = new OrderDaoImpl();
    OrderDetailDao orderDetailDao=new OrderDetailDaoImpl();
    ModelMapper modelMapper = new ModelMapper();


    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException {
            Connection connection= null;   //
        try {
            connection=DBConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

            boolean save = orderDao.save(new Orders(orderDTO.getOId(),orderDTO.getCustomerId(),orderDTO.getDate()));
            if(!save){
                connection.rollback();
                return false;
            }else{
              List<OrderDetail> orderDetails= modelMapper.map(orderDTO.getOrderDetailDTO(), new TypeToken<List<OrderDetail>>(){}.getType());
                for (OrderDetail o : orderDetails) {
                    if (!orderDetailDao.save(o)) {
                        connection.rollback();
                        return false;
                    }
                }
                connection.commit();
                return true;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;

    }
}
