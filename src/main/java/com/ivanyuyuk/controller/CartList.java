package com.ivanyuyuk.controller;


import com.ivanyuyuk.dao.HibernateConnection;
import com.ivanyuyuk.entity.Order;
import com.ivanyuyuk.entity.Product;
import com.ivanyuyuk.service.ZooStoreService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartList extends HttpServlet {
    private ZooStoreService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ZooStoreService(HibernateConnection.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession httpSession = req.getSession();
        Set<Long> choiceProduct = (Set<Long>) httpSession.getAttribute("choiceProduct");
        List<Product> products = productService.getProducts(choiceProduct);
        req.setAttribute("PRODUCT_CART", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("page/cart.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession httpSession = req.getSession();
        Set<Long> choiceProduct = (Set<Long>) httpSession.getAttribute("choiceProduct");
        if ("deleteProduct".equals(req.getParameter("typeCommand"))) {
            deleteProduct(req, choiceProduct);
        }
        if ("order".equals(req.getParameter("typeCommand"))) {
            makeOrder(req, choiceProduct);
            choiceProduct = null;
            httpSession.setAttribute("choiceProduct",choiceProduct);
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    private void deleteProduct(HttpServletRequest req, Set<Long> choiceProduct) {
        Long idProduct = Long.parseLong(req.getParameter("idProduct"));
        choiceProduct.remove(idProduct);
    }

    private void makeOrder(HttpServletRequest req, Set<Long> orderProducts) {
        String name = req.getParameter("name");
        String mail = req.getParameter("mail");
        String address = req.getParameter("address");
        String comment = req.getParameter("comment");
        Order order = new Order(name, mail, address, comment);
        productService.saveOrder(order, orderProducts);
    }

    @Override
    public void destroy() {
        if(HibernateConnection.getConnection()!=null){
            HibernateConnection.closeConnection();
        }
    }


}
