package lk.ijse.gdse.pos.dao.util;

import lk.ijse.gdse.pos.dao.SuperDao;
import lk.ijse.gdse.pos.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.gdse.pos.dao.custom.impl.ItemDaoImpl;
import lk.ijse.gdse.pos.dao.custom.impl.OrderDaoImpl;
import lk.ijse.gdse.pos.dao.custom.impl.OrderDetailDaoImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDao getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDaoImpl();
            case ITEM:
                return new ItemDaoImpl();
            case ORDER:
                return new OrderDaoImpl();
            case ORDERDETAIL:
                return new OrderDetailDaoImpl();
        }
        return null;
    }



}
