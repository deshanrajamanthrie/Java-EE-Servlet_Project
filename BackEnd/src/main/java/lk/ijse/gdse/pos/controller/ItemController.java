package lk.ijse.gdse.pos.controller;

import jakarta.json.*;
import lk.ijse.gdse.pos.bo.custom.ItemBo;
import lk.ijse.gdse.pos.bo.custom.impl.ItemBoImpl;
import lk.ijse.gdse.pos.dto.CustomerDTO;
import lk.ijse.gdse.pos.dto.ItemDTO;
import lk.ijse.gdse.pos.util.Status;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/item")
public class ItemController extends HttpServlet {
    ItemBoImpl itemBo = new ItemBoImpl();
    String message1 = ":";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveAndUpdateItem(req, resp, Status.SAVE);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        saveAndUpdateItem(req, resp, Status.UPDATE);
    }

    private void saveAndUpdateItem(HttpServletRequest req, HttpServletResponse resp, Status status) throws IOException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        try {
            resp.setContentType("application/json");
            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jsonObject = reader.readObject();
            ItemDTO dto = new ItemDTO(
                    jsonObject.getString("code"),
                    jsonObject.getString("itemName"),
                    jsonObject.getInt("qty"),
                    jsonObject.getJsonNumber("unitPrice").doubleValue()
            );
            switch (status) {
                case SAVE:
                    itemBo.saveItem(dto);
                case UPDATE:
                    itemBo.updateItem(dto);
            }
            if (dto != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                objectBuilder.add("status", true);
                objectBuilder.add(message1, "Succesed!");
            } else {

                objectBuilder.add(message1, "Invalid Input!");
                throw new RuntimeException("Invalid Input");
            }
        } catch (Throwable e) {
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            objectBuilder.add("status", false);
            objectBuilder.add(message1, e.toString());
            //   e.printStackTrace();
        } finally {
            resp.getWriter().println(objectBuilder.build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        String code = req.getParameter("code");
        try {
            boolean isId = itemBo.deleteItem(code);
            if (isId) {

                objectBuilder.add("Status", true);
                objectBuilder.add(message1, "Delete Success");
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (SQLException | ClassNotFoundException e) {
            objectBuilder.add("Status", false);
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            //e.printStackTrace();
            objectBuilder.add(message1, e.getMessage());
        } finally {
            resp.getWriter().println(objectBuilder.build());
        }
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        if(status .equals("GET")||status .equals("SEARCH")){
            switch (status){
                case "GET":
                    List<ItemDTO> allItem = itemBo.getAllItem();
                    //System.out.println(allItem);
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    for (ItemDTO itemDTO: allItem) {
                        if(itemDTO!=null) {
                            objectBuilder.add("code", itemDTO.getCode());
                            objectBuilder.add("itemName", itemDTO.getItemName());
                            objectBuilder.add("qty", itemDTO.getQty());
                            objectBuilder.add("unitPrice", itemDTO.getUnitPrice());
                            arrayBuilder.add(objectBuilder.build());
                        }
                        else {
                            objectBuilder.add(message1,"Empty Object!");
                            objectBuilder.add("Status",false);
                        }
                    }
                    resp.getWriter().println(arrayBuilder.build());
                    resp.getWriter().println(objectBuilder.build());
                    break;
                case "SEARCH":
                    String code = req.getParameter("code");
                    System.out.println("Code:"+code);
                    ResultSet resultSet = itemBo.searchItem(code);
                    System.out.println("Resultset :");
                    JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();
                    objectBuilder1.add("code",resultSet.getString(0));
                    objectBuilder1.add("itemName",resultSet.getString(1));
                    objectBuilder1.add("qty",resultSet.getInt(2));
                    objectBuilder1.add("unitPrice",resultSet.getDouble(3));
                    resp.getWriter().println(objectBuilder1.build());
            }
        }else{
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status",false);
            objectBuilder.add(message1, "Invalid Status!");
            resp.getWriter().println(objectBuilder.build());
        }

    }
}
