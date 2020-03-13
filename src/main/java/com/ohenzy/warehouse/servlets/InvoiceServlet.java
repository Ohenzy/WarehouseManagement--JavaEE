package com.ohenzy.warehouse.servlets;

import com.ohenzy.warehouse.models.Invoice;
import com.ohenzy.warehouse.storage.InvoiceStorage;
import com.ohenzy.warehouse.storage.PartnerStorage;
import com.ohenzy.warehouse.storage.WarehouseStorage;
import com.ohenzy.warehouse.storage.util.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@WebServlet("/invoices")
public class InvoiceServlet extends HttpServlet {

    private final String patternFormat = "yyyy-MM-dd";

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if(!action.equals("")){
            if(action.equals("add")){
                SimpleDateFormat format = new SimpleDateFormat();
                format.applyPattern(patternFormat);
                Date date = null;
                try {
                    date = format.parse(req.getParameter("date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                JsonParser parser = new JsonParser(req.getParameter("json_products"));
                invoices.add(new Invoice(
                        date,
                        req.getParameter("type_invoice"),
                        partners.findById(Integer.parseInt(req.getParameter("partner"))),
                        parser.getInvoiceProducts())
                );
            }  else if (action.equals("delete")){
                invoices.deleteById(req.getParameter("id_row"));
            } else if (action.equals("delete_all")){
                invoices.deleteAll();
            }
        }
        resp.sendRedirect("/invoices");
    }
}
