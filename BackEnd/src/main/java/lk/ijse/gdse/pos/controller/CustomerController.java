package lk.ijse.gdse.pos.controller;

import jakarta.json.*;
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
import java.sql.*;
import java.util.List;

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


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String status = req.getParameter("status");
        if (status.equals("GET") || status.equals("SEARCH")) {
            switch (status) {
                case "GET":
                    List<CustomerDTO> allCustomer = customerBo.getAllCustomer();
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    for (CustomerDTO dto : allCustomer) {     // all customer put in to the Customer DTO
                        objectBuilder.add("id", dto.getId());       // create to the Json type
                        objectBuilder.add("name", dto.getName());
                        objectBuilder.add("address", dto.getAddress());
                        objectBuilder.add("contact", dto.getContact());
                        arrayBuilder.add(objectBuilder.build());   // Object builder (Data)====> ArrayBuilder
                    }
                    resp.getWriter().println(arrayBuilder.build());  //All customer data recive like as response
                    break;
                case "SEARCH":
                    ResultSet resultSet = customerBo.searchCustomer(req.getParameter("id"));

                    JsonObjectBuilder objectBuilder1 = Json.createObjectBuilder();
                    objectBuilder1.add("id", resultSet.getString(1));
                    objectBuilder1.add("name", resultSet.getString(2));
                    objectBuilder1.add("address", resultSet.getString(3));
                    objectBuilder1.add("contact", resultSet.getString(4));
                    resp.getWriter().println(objectBuilder1.build());
            }
        } else {
            JsonObjectBuilder objectBuilder3 = Json.createObjectBuilder();
            objectBuilder3.add("Status", false);
            objectBuilder3.add(message, "Invalid Status!");
            resp.getWriter().println(objectBuilder3.build());
            throw new RuntimeException ();

        }


    }

}
