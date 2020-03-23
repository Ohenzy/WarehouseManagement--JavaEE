package com.ohenzy.warehouse.servlets;

import com.ohenzy.warehouse.models.Warehouse;
import com.ohenzy.warehouse.storage.Storage;
import com.ohenzy.warehouse.storage.hibernate.WarehouseStorageHibernate;
import com.ohenzy.warehouse.storage.jdbc.WarehouseStorageJDBC;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/warehouse")
public class WarehouseServlet extends HttpServlet {

    private final Storage<Warehouse> warehouses = new WarehouseStorageHibernate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("warehouses", warehouses.findAll());
        req.getRequestDispatcher("/views/warehouse.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if(!action.equals("")){
            if(action.equals("add")){
                warehouses.save(new Warehouse(req.getParameter("name"), req.getParameter("address"), req.getParameter("phone")));
            } else if(action.equals("edit")){
                warehouses.save(new Warehouse(Integer.parseInt(req.getParameter("id_row")), req.getParameter("name"), req.getParameter("address"), req.getParameter("phone")));
            } else if (action.equals("delete")){
                warehouses.deleteById(Integer.parseInt(req.getParameter("id_row")));
            } else if (action.equals("delete_all")){
                warehouses.deleteAll();
            }
        }
        resp.sendRedirect("/warehouse");
    }
}
