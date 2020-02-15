package com.ohenzy.warehouse.servlets;

import com.ohenzy.warehouse.models.Product;
import com.ohenzy.warehouse.storage.ProductStorage;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private final ProductStorage products = new ProductStorage();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", products.findAll());
        req.getRequestDispatcher("/views/products.jsp").forward(req,resp);
    }

}
