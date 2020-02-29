package com.ohenzy.warehouse.servlets;

import com.ohenzy.warehouse.storage.InvoiceStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/invoices")
public class InvoiceServlet extends HttpServlet {

    private final InvoiceStorage invoices = new InvoiceStorage();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("invoices", invoices.findAll());
        req.getRequestDispatcher("/views/invoices.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        resp.sendRedirect("/invoices");
    }
}
