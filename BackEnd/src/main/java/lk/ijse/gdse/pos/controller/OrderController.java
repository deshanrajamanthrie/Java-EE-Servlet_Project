package lk.ijse.gdse.pos.controller;


import jakarta.json.*;
import lk.ijse.gdse.pos.bo.custom.impl.PurchaseOrderBOImpl;
import lk.ijse.gdse.pos.dto.OrderDTO;
import lk.ijse.gdse.pos.dto.OrderDetailDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/orders")
public class OrderController extends HttpServlet {
    PurchaseOrderBOImpl purchaseOrderBO = new PurchaseOrderBOImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //-------------------------------------------------------------------------
        saveOrder(req, resp);


    }

    private void saveOrder(HttpServletRequest req, HttpServletResponse resp) {
        //   JsonReader reader = null;
        try {
            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jsonObject = reader.readObject();
            List<OrderDetailDTO> list = new ArrayList<>();
            String orderId = jsonObject.getString("oId");
            OrderDTO orderDTO = new OrderDTO(
                    orderId,
                    jsonObject.getString("customerId"),
                    Date.valueOf(jsonObject.getString("date")
                    )

            );
            System.out.println("List :"+list);
            JsonArray orderDetailArray = jsonObject.getJsonArray("orderDetail");
            for (JsonValue jsonValue : orderDetailArray) {
                JsonObject jsonObject1 = jsonValue.asJsonObject();
                list.add(new OrderDetailDTO(
                        orderId,
                        jsonObject1.getString("itemCode"),
                        jsonObject1.getJsonNumber("unitPrice").doubleValue(),
                        jsonObject1.getInt("qty")
                ));
            }

            orderDTO.setOrderDetailDTO(list);


            purchaseOrderBO.saveOrder(orderDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
