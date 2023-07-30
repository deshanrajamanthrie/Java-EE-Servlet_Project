package lk.ijse.gdse.pos.controller;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse.pos.bo.custom.impl.CustomerBoImpl;
import lk.ijse.gdse.pos.db.DBConnection;
import lk.ijse.gdse.pos.dto.CustomerDTO;
import lk.ijse.gdse.pos.entity.Customer;

import lk.ijse.gdse.pos.util.Status;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(urlPatterns = "/customer")
public class CustomerController extends HttpServlet {

    CustomerBoImpl customerBo = new CustomerBoImpl();
    String message = "";


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
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder(); //use for ==>we can use create object Manually
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
            switch (status) {
                case SAVE:
                    customerBo.saveCustomer(customerDTO);
                case UPDATE:
                    customerBo.updateCustomer(customerDTO);
            }
            if (customerDTO != null) {
                objectBuilder.add("status", true);
                objectBuilder.add(message, "Successed!");
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                objectBuilder.add(message, "Invalid Input");
                throw new RuntimeException("Invalid Input....");
            }
        } catch (Throwable e) {
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            objectBuilder.add("status", false);
            objectBuilder.add(message, e.toString());
            e.printStackTrace();
        } finally {
            resp.getWriter().println(objectBuilder.build()); //=====parse the object (mannually create)for that use getWritter
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        try {
            String id = req.getParameter("id");
            System.out.println("CustId:" + id);
            if (customerBo.deleteCustomer(id)) {
                objectBuilder.add("status", true);
                objectBuilder.add("", "success");
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
            objectBuilder.add("status", "false");
            objectBuilder.add(message, e.getMessage());
            e.printStackTrace();
        } finally {    // all ready calls
            resp.getWriter().println(objectBuilder.build());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        if (status.equals("GET") || status.equals("SEARCH")) {
            switch (status){
                case "GET":
                    CUS
            }
        }
    }
}
