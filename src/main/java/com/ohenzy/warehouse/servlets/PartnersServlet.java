package com.ohenzy.warehouse.servlets;

import com.ohenzy.warehouse.models.Partner;
import com.ohenzy.warehouse.storage.PartnerStorage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
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

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (!action.equals(""))
            if (action.equals("add")) {
                partners.save(new Partner(req.getParameter("nameOrg"), req.getParameter("nameDirector"), req.getParameter("address"), req.getParameter("phone"),
                        req.getParameter("email"), req.getParameter("inn"), req.getParameter("ogrn")));
            } else if (action.equals("edit")) {
                partners.edit(new Partner(Integer.parseInt(req.getParameter("id_row")),req.getParameter("nameOrg"), req.getParameter("nameDirector"), req.getParameter("address"), req.getParameter("phone"),
                        req.getParameter("email"), req.getParameter("inn"), req.getParameter("ogrn")));
            } else if (action.equals("delete")){
                partners.deleteById(req.getParameter("id_row"));
            } else if (action.equals("delete_all")){
                partners.deleteAll();
            }

        resp.sendRedirect("/partners");
    }
}
