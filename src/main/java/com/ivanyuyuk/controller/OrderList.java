package com.ivanyuyuk.controller;

import com.ivanyuyuk.dao.HibernateConnection;
import com.ivanyuyuk.entity.Order;
import com.ivanyuyuk.service.ZooStoreService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderList extends HttpServlet {
    private ZooStoreService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ZooStoreService(HibernateConnection.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("COMPLETE_ORDER".equals(req.getParameter("command"))){
            Long orderId=Long.parseLong(req.getParameter("orderId"));
            productService.markOrderIsComplete(orderId);
            resp.sendRedirect(req.getContextPath() + "/orders");
        }else{
            List<Order> orders = productService.getOrders();
            req.setAttribute("ORDERS", orders);
            RequestDispatcher dispatcher = req.getRequestDispatcher("page/list-orders.jsp");
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
