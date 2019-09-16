package com.ivanyuyuk.controller;


import com.ivanyuyuk.dao.HibernateConnection;
import com.ivanyuyuk.entity.Product;
import com.ivanyuyuk.service.ZooStoreService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/warehouse")
public class Warehouse extends HttpServlet {
    private ZooStoreService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ZooStoreService(HibernateConnection.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("changeCountProduct".equals(req.getParameter("command"))){
            Long countProduct=Long.parseLong(req.getParameter("newCountProduct"));
            Long productId=Long.parseLong(req.getParameter("productId"));
            productService.setCountProduct(productId, countProduct);
            resp.sendRedirect(req.getContextPath() + "/warehouse");
        }else{
            List<Product> warehouse=productService.getProducts();
            req.setAttribute("PRODUCTS",warehouse);
            RequestDispatcher dispatcher = req.getRequestDispatcher("page/list-warehouse.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        if(HibernateConnection.getConnection()!=null){
            HibernateConnection.closeConnection();
        }
    }
}
