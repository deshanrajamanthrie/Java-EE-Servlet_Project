package lk.ijse.gdse.pos.controller;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import lk.ijse.gdse.pos.bo.BOFactory;
import lk.ijse.gdse.pos.bo.BOTypes;
import lk.ijse.gdse.pos.bo.SuperBo;
import lk.ijse.gdse.pos.bo.custom.OrderDetailBO;
import lk.ijse.gdse.pos.bo.custom.impl.OrderDetailBOImpl;
import lk.ijse.gdse.pos.dto.OrderDetailDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/orderDetail")
public class OrderDetailController extends HttpServlet {
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getInstance().getBo(BOTypes.ORDERDETAIL);
    final String message = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<OrderDetailDTO> all = orderDetailBO.getAll();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            //    OrderDetailDTO od = new OrderDetailDTO();
            for (OrderDetailDTO dto : all) {
                if (dto != null) {
                    objectBuilder.add("oid", dto.getItemCode());
                    objectBuilder.add("itemCode", dto.getItemCode());
                    objectBuilder.add("unitPrice", dto.getUnitPrice());
                    objectBuilder.add("qty", dto.getQty());
                    arrayBuilder.add(objectBuilder);
                } else {
                    objectBuilder.add(message, "Loading Failed!");
                }

            }
            resp.getWriter().println(arrayBuilder.build());
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
    }
}
