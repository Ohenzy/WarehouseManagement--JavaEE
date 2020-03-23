package com.ohenzy.warehouse.servlets;

import com.ohenzy.warehouse.models.Invoice;
import com.ohenzy.warehouse.models.Partner;
import com.ohenzy.warehouse.models.Product;
import com.ohenzy.warehouse.models.Warehouse;
import com.ohenzy.warehouse.storage.Storage;
import com.ohenzy.warehouse.storage.jdbc.InvoiceStorageJDBC;
import com.ohenzy.warehouse.storage.jdbc.PartnerStorageJDBC;
import com.ohenzy.warehouse.storage.jdbc.ProductStorageJDBC;
import com.ohenzy.warehouse.storage.jdbc.WarehouseStorageJDBC;
import com.ohenzy.warehouse.storage.util.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/invoices")
public class InvoiceServlet extends HttpServlet {

    private final String htmlDateFormat = "yyyy-MM-dd";

    private final Storage<Invoice> invoices = new InvoiceStorageJDBC();
    private final Storage<Partner> partners = new PartnerStorageJDBC();
    private final Storage<Warehouse> warehouses = new WarehouseStorageJDBC();
    private final Storage<Product> products = new ProductStorageJDBC();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("serverDate", new SimpleDateFormat(htmlDateFormat).format(new Date()));
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
                format.applyPattern(htmlDateFormat);
                Date date = null;
                try {
                    date = format.parse(req.getParameter("date"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                JsonParser parser = new JsonParser(req.getParameter("json_products"));
                invoices.save(new Invoice(
                        date,
                        req.getParameter("type_invoice"),
                        partners.findById(Integer.parseInt(req.getParameter("partner"))),
                        parser.getInvoiceProducts())
                );
                for (Product product : parser.getProducts())
                    products.save(product);
            } else if (action.equals("delete")){
                int id = Integer.parseInt(req.getParameter("id_row"));
                if( invoices.existsById(id))
                    invoices.deleteById(id);
            } else if (action.equals("delete_all")){
                invoices.deleteAll();
            }
        }
        resp.sendRedirect("/invoices");
    }
}
