package lk.ijse.dep7.servlet_pos_app.api;

import java.io.*;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lk.ijse.dep7.servlet_pos_app.dto.CustomerDTO;

@WebServlet(value = "/customers")
public class CustomerServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        CustomerDTO customer = new CustomerDTO("C002", "Dilani", "Galle");
        Jsonb jsonb = JsonbBuilder.create();
        String s = jsonb.toJson(customer);
        out.println(s);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getContentType().equals("application/json")){
            /*TODO: send an error massage*/
        }else{
            StringBuilder body = new StringBuilder();
            req.getReader().lines().forEach(l -> body.append(l + "\n"));
            System.out.println(body.toString());
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customer = jsonb.fromJson(body.toString(), CustomerDTO.class);
            System.out.println(customer.getId());
            System.out.println(customer.getName());
            System.out.println(customer.getAddress());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Put Request");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Delete Request");
    }
}