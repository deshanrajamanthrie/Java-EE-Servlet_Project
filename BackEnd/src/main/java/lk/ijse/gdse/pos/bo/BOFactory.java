package lk.ijse.gdse.pos.bo;

import lk.ijse.gdse.pos.bo.custom.impl.CustomerBoImpl;
import lk.ijse.gdse.pos.bo.custom.impl.ItemBoImpl;
import lk.ijse.gdse.pos.bo.custom.impl.OrderDetailBOImpl;
import lk.ijse.gdse.pos.bo.custom.impl.PurchaseOrderBOImpl;

public class BOFactory {
    public static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBo getBo(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER:
                return new CustomerBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case PURCHASE:
                return new PurchaseOrderBOImpl();
            case ORDERDETAIL:
                return new OrderDetailBOImpl();
        }
        return null;
    }


}
