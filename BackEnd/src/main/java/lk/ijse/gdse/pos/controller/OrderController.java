package lk.ijse.gdse.pos.controller;


import jakarta.json.*;
import lk.ijse.gdse.pos.bo.BOFactory;
import lk.ijse.gdse.pos.bo.BOTypes;
import lk.ijse.gdse.pos.bo.SuperBo;
import lk.ijse.gdse.pos.bo.custom.PurchaseOrderBO;
import lk.ijse.gdse.pos.bo.custom.impl.PurchaseOrderBOImpl;
import lk.ijse.gdse.pos.dto.OrderDTO;
import lk.ijse.gdse.pos.dto.OrderDetailDTO;
import org.glassfish.json.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/orders")
public class OrderController extends HttpServlet {
    final String message = "";
    PurchaseOrderBO purchaseOrderBO = (PurchaseOrderBO) BOFactory.getInstance().getBo(BOTypes.PURCHASE);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //-------------------------------------------------------------------------

        try {
            saveOrder(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("oId");
        OrderDTO orderDTO = new OrderDTO(
                id,
                jsonObject.getString("customerId"),
                Date.valueOf(jsonObject.getString("date"))
        );
        System.out.println(orderDTO.getOId() + " " + orderDTO.getCustomerId() + " " + orderDTO.getDate());
        JsonArray orderdetail = jsonObject.getJsonArray("orderdetail");  //get reference from above object
        ArrayList<OrderDetailDTO> list = new ArrayList<>();

        for (JsonValue jsonValue : orderdetail) {
            JsonObject jsonObject1 = jsonValue.asJsonObject();  //Json Value bind the Json Object
            list.add(new OrderDetailDTO(
                    id,
                    jsonObject1.getString("itemCode"),
                    jsonObject1.getJsonNumber("unitPrice").doubleValue(),
                    jsonObject1.getInt("qty")
            ));
        }
        orderDTO.setOrderDetailDTO(list);   //add the Json Array (Order Detail List)
        if (orderDTO != null) {
            try {
                boolean isSavedOrder = purchaseOrderBO.saveOrder(orderDTO);
                if (isSavedOrder) {
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("code", 200);
                    response.add("Message", "Save Succesed!");
                    resp.getWriter().println(response.build());
                } else {
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("code", 400);
                    response.add("Message", "Save Failed!");
                    resp.getWriter().println(response.build());
                }
            } catch (Throwable e) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("code", 500);
                response.add("Message", "Error");
                response.add("data", e.getLocalizedMessage());
                resp.getWriter().println(response.build());

            }


        }



        
        

        /*JsonObjectBuilder obj = Json.createObjectBuilder();
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
            if (orderDTO != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                obj.add(message, "Order Save Succesed!");
                purchaseOrderBO.saveOrder(orderDTO);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                obj.add(message, "Order Not Saved! ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            obj.add(message, e.getMessage());
        } finally {
            resp.getWriter().println(obj.build());
        }
*/
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateOreder(req, resp);
    }

    private void updateOreder(HttpServletRequest req, HttpServletResponse resp) {

        try {
            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jsonObject = reader.readObject();
            List<OrderDetailDTO> orderDetailList = new ArrayList<>();

            String orderId = jsonObject.getString("oid");
            OrderDTO orderDTO = new OrderDTO(
                    orderId,
                    jsonObject.getString("customerId"),
                    Date.valueOf(jsonObject.getString("date"))
            );
            JsonArray orderDetail = jsonObject.getJsonArray("orderDetail");
            for (JsonValue jsonValue : orderDetail) {
                JsonObject jsonObject1 = jsonValue.asJsonObject();
                OrderDetailDTO orderDetailDTO = new OrderDetailDTO(
                        jsonObject1.getString(orderId),
                        jsonObject1.getString("itemCode"),
                        jsonObject1.getJsonNumber("unitPrice").doubleValue(),
                        jsonObject1.getInt("qty")

                );
                orderDetailList.add(orderDetailDTO);
            }
            orderDTO.setOrderDetailDTO(orderDetailList);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<OrderDTO> allOrders = purchaseOrderBO.getAll();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (OrderDTO dto : allOrders) {
                if (dto != null) {
                    objectBuilder.add("oId", dto.getOId());
                    objectBuilder.add("itemName", dto.getCustomerId());
                    objectBuilder.add("date", dto.getDate().toString());
                    arrayBuilder.add(objectBuilder);
                } else {
                    objectBuilder.add(message, "Loading Failed!");
                }
            }
            resp.getWriter().println(arrayBuilder.build());
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
