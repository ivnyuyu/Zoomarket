package com.ivanyuyuk.service;

import com.ivanyuyuk.dao.OrderDao;
import com.ivanyuyuk.dao.OrderDaoImpl;
import com.ivanyuyuk.dao.ProductDao;
import com.ivanyuyuk.dao.ProductDaoImpl;
import com.ivanyuyuk.entity.Order;
import com.ivanyuyuk.entity.Product;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ZooStoreService {
    private ProductDao productDao;
    private OrderDao orderDao;

    public ZooStoreService(SessionFactory sessionFactory) {
        productDao = new ProductDaoImpl(sessionFactory);
        orderDao = new OrderDaoImpl(sessionFactory);
    }

    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    public List<Product> getProducts(Set<Long> cookie) {
        if (cookie == null) {
            return null;
        }
        List<Product> productList = new ArrayList<>();
        for (Long aLong : cookie) {
            productList.add(productDao.getProduct(aLong));
        }
        return productList;
    }

    public void saveOrder(Order order, Set<Long> productList) {
        if (productList == null || productList.size() == 0) {
            return;
        }
        orderDao.saveOrder(order, productList);
    }

    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    public void markOrderIsComplete(Long id) {
        orderDao.markOrderIsComplete(id);
    }

    public void setCountProduct(Long id, Long value) {
        orderDao.setCountProduct(id, value);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }

    public Product getProductById(Long id) {
        return productDao.getProduct(id);
    }

    public void updateProduct(Product product, Long id) {
        productDao.update(product, id);
    }

    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }
}
