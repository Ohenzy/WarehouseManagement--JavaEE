package com.ohenzy.warehouse.servlets;

import com.ohenzy.warehouse.storage.InvoiceStorage;
import com.ohenzy.warehouse.storage.PartnerStorage;
import com.ohenzy.warehouse.storage.WarehouseStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/invoices")
public class InvoiceServlet extends HttpServlet {

    private final String patternFormat = "dd.MM.yyyy";


    private final InvoiceStorage invoices = new InvoiceStorage();
    private final PartnerStorage partners = new PartnerStorage();
    private final WarehouseStorage warehouses = new WarehouseStorage();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("serverDate", new SimpleDateFormat(patternFormat).format(new Date()));
        req.setAttribute("invoices", invoices.findAll());
        req.setAttribute("partners", partners.findAll());
        req.setAttribute("warehouses", warehouses.findAll());
        req.getRequestDispatcher("/views/invoices.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if(!action.equals("")){
            if(action.equals("add")){
                invoices.add(null);
            }  else if (action.equals("delete")){
                invoices.deleteById(req.getParameter("id_row"));
            } else if (action.equals("delete_all")){
                invoices.deleteAll();
            }
        }
        resp.sendRedirect("/invoices");
    }
}
