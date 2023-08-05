package lk.ijse.gdse.pos.controller;


import jakarta.json.*;
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
            OrderDTO orderDTO = new OrderDTO(
                    jsonObject.getString("oId"),
                    jsonObject.getString("customerId"),
                    Date.valueOf(jsonObject.getString("date")
                    )
            );
            JsonArray orderDetailArray = jsonObject.getJsonArray("OrderDetail");
            for (JsonValue jsonValue : orderDetailArray) {
                JsonObject jsonObject1 = jsonValue.asJsonObject();
                jsonObject1.getString("oId");
                jsonObject1.getString("itemCode");
                jsonObject1.getString("unitPrice");
                jsonObject1.getString("qty");
                jsonObject1.getString("total");
            }
            orderDTO.setOrderDetailDTO(list);


          /*  JsonArray orderDetailArray = jsonObject.getJsonArray("orderDetail");
            for (JsonValue jsonValue : orderDetailArray) {
                JsonObject jsonObject1 = jsonValue.asJsonObject();
                new OrderDetailDTO();
            }
            orderDTO.setOrderDetailDTO(list);*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
