package com.ivanyuyuk.controller;

import com.ivanyuyuk.dao.HibernateConnection;
import com.ivanyuyuk.entity.Product;
import com.ivanyuyuk.service.ZooStoreService;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebServlet("/changeproduct")
@MultipartConfig
public class ProductChangeController extends HttpServlet {
    private ZooStoreService productService;

    @Override
    public void init() throws ServletException {
        super.init();
        productService = new ZooStoreService(HibernateConnection.getConnection());
    }

    @Override
    public void destroy() {
        if (HibernateConnection.getConnection() != null) {
            HibernateConnection.closeConnection();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if ("ADD".equals(req.getParameter("command"))) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("page/add-product.jsp");
            dispatcher.forward(req, resp);
        } else if ("DELETE".equals(req.getParameter("command"))) {
            Long idProduct = Long.parseLong(req.getParameter("productId"));
            productService.deleteProduct(idProduct);
            resp.sendRedirect(req.getContextPath() + "/changeproduct");
        } else if ("UPDATE".equals(req.getParameter("command"))) {
            Long idProduct = Long.parseLong(req.getParameter("productId"));
            Product product = productService.getProductById(idProduct);
            req.setAttribute("THE_PRODUCT", product);
            RequestDispatcher dispatcher = req.getRequestDispatcher("page/update-product.jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("PRODUCTS", productService.getProducts());
            RequestDispatcher dispatcher = req.getRequestDispatcher("page/handle-product.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if ("updateProduct".equals(req.getParameter("updateProduct"))) {
            Long idProduct = Long.parseLong(req.getParameter("idProduct"));
            Product updatedProduct = createProductFromReq(req);
            productService.updateProduct(updatedProduct, idProduct);
        } else if ("addProduct".equals(req.getParameter("addProduct"))) {
            Product newProduct=createProductFromReq(req);
            productService.saveProduct(newProduct);
        }
        resp.sendRedirect(req.getContextPath() + "/changeproduct");
    }

    private Product createProductFromReq(HttpServletRequest req) throws ServletException, IOException {
        String nameProduct = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        String photoName = req.getParameter("photoName");
        Part filePart = req.getPart("file");
        InputStream inputStream = filePart.getInputStream();
        BufferedImage image = ImageIO.read(inputStream);
        ServletContext servletContext = getServletContext();
        String newImageDir = "/img/" + photoName + ".jpg";
        String filepath = servletContext.getRealPath(newImageDir);
        Properties properties = new Properties();
        try (InputStream propertiesStream = ProductChangeController.class.getClassLoader().getResourceAsStream("photoSource.properties")) {
            properties.load(propertiesStream);
        }
        String photoUrl = properties.getProperty("photo.url");
        if (photoUrl != null && photoName != null && image != null) {
            ImageIO.write(image, "jpg", new File(photoUrl + "\\img\\" + photoName + ".jpg"));
            ImageIO.write(image, "jpg", new File(filepath));
            return new Product(nameProduct, description, "/img/" + photoName + ".jpg", price);
        } else {
            return new Product(nameProduct, description, null, price);
        }
    }
}
