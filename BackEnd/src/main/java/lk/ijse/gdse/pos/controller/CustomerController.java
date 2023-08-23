package lk.ijse.gdse.pos.controller;

import jakarta.json.*;
import lk.ijse.gdse.pos.bo.BOFactory;
import lk.ijse.gdse.pos.bo.BOTypes;
import lk.ijse.gdse.pos.bo.custom.CustomerBo;
import lk.ijse.gdse.pos.bo.custom.impl.CustomerBoImpl;
import lk.ijse.gdse.pos.dto.CustomerDTO;

import lk.ijse.gdse.pos.util.Status;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {


    CustomerBo customerBo = (CustomerBo) BOFactory.getInstance().getBo(BOTypes.CUSTOMER);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            saveAndUpdateCustomer(req, resp, Status.SAVE);   //return req and resp and Enum
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            saveAndUpdateCustomer(req, resp, Status.UPDATE);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private void saveAndUpdateCustomer(HttpServletRequest req, HttpServletResponse resp, Status status) throws IOException {
        try {

            resp.setContentType("application/json");  //check that resp type Json type or not
            JsonReader reader = Json.createReader(req.getReader());  // read the request
            JsonObject jsonObject = reader.readObject();
            CustomerDTO customerDTO = new CustomerDTO(
                    jsonObject.getString("id"),
                    jsonObject.getString("name"),
                    jsonObject.getString("address"),
                    jsonObject.getString("contact")
            );
            Boolean isSaved;
            Boolean isUpdated;
            switch (status) {
                case SAVE:
                    isSaved = customerBo.saveCustomer(customerDTO);
                    saveCustomer(isSaved, resp);
                    break;
                case UPDATE:
                    isUpdated = customerBo.updateCustomer(customerDTO);
                    updateCustomer(isUpdated, resp);
            }
            //===========================Saved Response
        } catch (Throwable e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 500);
            response.add("Message", "Error");
            response.add("Data", e.getLocalizedMessage());
            resp.getWriter().println(response.build());
        }
    }

    private void saveCustomer(Boolean isSaved, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        if (isSaved) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 200);
            response.add("Message", "Save Done!");
            resp.getWriter().println(response.build());
        } else {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 400);
            response.add("Message", "Save Failed!");
            resp.getWriter().println(response.build());
        }

    }

    private void updateCustomer(Boolean isUpdated, HttpServletResponse resp) throws IOException {
        resp.setStatus(200);
        if (isUpdated) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 200);
            response.add("Message", "Update Done!");
            resp.getWriter().println(response.build());
        } else {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 400);
            response.add("Message", "Update Failed!");
            resp.getWriter().println(response.build());

        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(200);
        try {
            String id = req.getParameter("id");
            System.out.println("CustId:" + id);
            try {
                if (customerBo.deleteCustomer(id)) {
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    // resp.setStatus(200);
                    response.add("code", 200);
                    response.add("Massage", "Delete Successed!");
                    resp.getWriter().println(response.build());
                } else {
                    JsonObjectBuilder response = Json.createObjectBuilder();
                    // resp.setStatus(200);
                    response.add("code", 400);
                    response.add("Massage", "Delete Failed!");
                    resp.getWriter().println(response.build());
                }
            } catch (ClassNotFoundException e) {
                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("code", 500);
                response.add("Message", "Error");
                response.add("data", e.getLocalizedMessage());
                resp.getWriter().println(response.build());
            }
        } catch (SQLException e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 500);
            response.add("Message", "Error");
            response.add("data", e.getLocalizedMessage());
            resp.getWriter().println(response.build());
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        resp.setStatus(200);
        try {
            List<CustomerDTO> allCustomer = customerBo.getAllCustomer();
            for (CustomerDTO dto : allCustomer) {
                objectBuilder.add("id", dto.getId());
                objectBuilder.add("name", dto.getName());
                objectBuilder.add("address", dto.getAddress());
                objectBuilder.add("contact", dto.getContact());
                arrayBuilder.add(objectBuilder);
            }

            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 200);
            response.add("Massage", "Succesed Get all!");
            response.add("data", arrayBuilder);
            resp.getWriter().println(response.build());

        } catch (Throwable e) {
            JsonObjectBuilder response = Json.createObjectBuilder();
            response.add("code", 500);
            response.add("Massage", "Error");
            response.add("data", e.getLocalizedMessage());
            resp.getWriter().println(response.build());
        }


    }

}
