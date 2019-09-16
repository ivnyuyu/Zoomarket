package com.ivanyuyuk.controller;

import com.ivanyuyuk.dao.HibernateConnection;
import com.ivanyuyuk.entity.Product;
import com.ivanyuyuk.service.ZooStoreService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/productlist")
public class ProductList extends HttpServlet {

    private ZooStoreService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ZooStoreService(HibernateConnection.getConnection());
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        List<Product> products=productService.getProducts();

        req.setAttribute("PRODUCT_LIST", products);

        RequestDispatcher dispatcher = req.getRequestDispatcher("page/list-products.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idProduct=req.getParameter("idProduct");
        HttpSession httpSession=req.getSession();
        Set<Long> choiceProduct=(Set<Long>) httpSession.getAttribute("choiceProduct");
        if(choiceProduct==null){
            choiceProduct=new HashSet<>();
        }
        choiceProduct.add(Long.parseLong(idProduct));
        httpSession.setAttribute("choiceProduct",choiceProduct);
        resp.sendRedirect(req.getContextPath() + "/productlist");
    }

    @Override
    public void destroy() {
       if(HibernateConnection.getConnection()!=null){
           HibernateConnection.closeConnection();
       }
    }
}
