package lk.ijse.gdse.pos.bo.custom.impl;

import lk.ijse.gdse.pos.bo.custom.OrderDetailBO;
import lk.ijse.gdse.pos.dao.SuperDao;
import lk.ijse.gdse.pos.dao.custom.OrderDetailDao;
import lk.ijse.gdse.pos.dao.custom.impl.OrderDetailDaoImpl;
import lk.ijse.gdse.pos.dao.util.DAOFactory;
import lk.ijse.gdse.pos.dao.util.DAOTypes;
import lk.ijse.gdse.pos.dto.OrderDetailDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {
    ModelMapper modelMapper = new ModelMapper();

    OrderDetailDao orderDetailDao = (OrderDetailDao) DAOFactory.getInstance().getDAO(DAOTypes.ORDERDETAIL);

    public List<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return modelMapper.map(orderDetailDao.getAll(), new TypeToken<List<OrderDetailDTO>>() {
        }.getType());
    }
}
