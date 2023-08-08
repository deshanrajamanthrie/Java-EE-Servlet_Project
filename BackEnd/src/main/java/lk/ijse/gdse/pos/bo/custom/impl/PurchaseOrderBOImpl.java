package lk.ijse.gdse.pos.bo.custom.impl;

import lk.ijse.gdse.pos.bo.custom.PurchaseOrderBO;
import lk.ijse.gdse.pos.dao.SuperDao;
import lk.ijse.gdse.pos.dao.custom.OrderDao;
import lk.ijse.gdse.pos.dao.custom.OrderDetailDao;
import lk.ijse.gdse.pos.dao.custom.impl.OrderDaoImpl;
import lk.ijse.gdse.pos.dao.custom.impl.OrderDetailDaoImpl;
import lk.ijse.gdse.pos.dao.util.DAOFactory;
import lk.ijse.gdse.pos.dao.util.DAOTypes;
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
    ModelMapper modelMapper = new ModelMapper();
    OrderDao orderDao = (OrderDao) DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
    OrderDetailDao  orderDetailDao= (OrderDetailDao) DAOFactory.getInstance().getDAO(DAOTypes.ORDERDETAIL);


    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException {

        Connection connection = null;
        try {

            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean save = orderDao.save(new Orders(orderDTO.getOId(), orderDTO.getCustomerId(), orderDTO.getDate()));
            if (!save) {
                connection.rollback();
                return false;
            } else {
                List<OrderDetail> orderDetail = modelMapper.map(orderDTO.getOrderDetailDTO(), new TypeToken<List<OrderDetail>>() {
                }.getType());
                // modelMapper.map(OrderDetailDTO)
                for (OrderDetail orderDetail1 : orderDetail) {
                    if (!orderDetailDao.save(orderDetail1)) {
                        connection.rollback();
                        return false;
                    }

                }
                connection.commit();
                return true;
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
     /*Connection connection= null;
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
        return false;*/

    }

    public List<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return modelMapper.map(orderDao.getAll(), new TypeToken<List<OrderDTO>>() {
        }.getType());
    }
}
