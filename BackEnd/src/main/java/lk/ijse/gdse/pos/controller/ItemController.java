package lk.ijse.gdse.pos.controller;

import jakarta.json.*;
import lk.ijse.gdse.pos.bo.BOFactory;
import lk.ijse.gdse.pos.bo.BOTypes;
import lk.ijse.gdse.pos.bo.SuperBo;
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
    ItemBo itemBo = (ItemBo) BOFactory.getInstance().getBo(BOTypes.ITEM);
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
            Boolean isSaved = null;
            Boolean isUpdated = null;
            switch (status) {
                case SAVE:
                    isSaved = itemBo.saveItem(dto);
                    saveItemResponse(isSaved, resp);
                    break;
                case UPDATE:
                    isUpdated = itemBo.updateItem(dto);
                    updateItemResponse(isUpdated, resp);
                    break;
            }
        } catch (Throwable e) {
            resp.setStatus(200);
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 500);
            response.add("Message", "Error");
            response.add("data", e.getLocalizedMessage());
            resp.getWriter().println(response.build());
        }
    }

    private void updateItemResponse(Boolean isUpdated, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        if (isUpdated) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 200);
            response.add("Message", "Update Succesed!");
            resp.getWriter().println(response.build());
        } else {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 400);
            response.add("Message", "Update Failed!");
            resp.getWriter().println(response.build());
        }
    }


    private void saveItemResponse(Boolean isSaved, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        if (isSaved) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 200);
            response.add("Massage", "Save Succesed!");
            resp.getWriter().println(response.build());
        } else {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 400);
            response.add("Massage", "Save Failed!");
            resp.getWriter().println(response.build());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        resp.setStatus(200);
        try {
            if (itemBo.deleteItem(code)) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("code", 200);
                response.add("Message", "Delete Succesed!");
                resp.getWriter().println(response.build());
            } else {
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("code", 400);
                response.add("Message", "Delete Failed");
                resp.getWriter().println(response.build());
            }
        } catch (SQLException | ClassNotFoundException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 500);
            response.add("Message", "Error");
            response.add("data", e.getLocalizedMessage());
            resp.getWriter().println(response.build());
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(200);
        List<ItemDTO> allItem = null;
        try {
            allItem = itemBo.getAllItem();
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            for (ItemDTO dto : allItem) {
                objectBuilder.add("code", dto.getCode());
                objectBuilder.add("itemName", dto.getItemName());
                objectBuilder.add("qty", dto.getQty());
                objectBuilder.add("unitPrice", dto.getUnitPrice());
                arrayBuilder.add(objectBuilder.build());
            }
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 200);
            response.add("Message", "Succesed getAll");
            response.add("data", arrayBuilder);
            resp.getWriter().println(response.build());
        } catch (Throwable e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 500);
            response.add("Message", "Error");
            response.add("data", e.getLocalizedMessage());
            resp.getWriter().println(response.build());
        }
    }
}
