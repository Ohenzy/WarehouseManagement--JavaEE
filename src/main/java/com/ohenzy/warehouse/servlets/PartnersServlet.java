package com.ohenzy.warehouse.servlets;

import com.ohenzy.warehouse.storage.PartnerStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/partners")
public class PartnersServlet extends HttpServlet {

    private final PartnerStorage partners = new PartnerStorage();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("partners", partners.findAll());
        req.getRequestDispatcher("/views/partners.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
