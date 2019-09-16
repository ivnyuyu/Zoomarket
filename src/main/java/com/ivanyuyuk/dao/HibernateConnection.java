package com.ivanyuyuk.dao;

import com.ivanyuyuk.entity.Order;
import com.ivanyuyuk.entity.Product;
import com.ivanyuyuk.entity.ProductWarehouse;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {
    private static final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Product.class)
            .addAnnotatedClass(Order.class)
            .addAnnotatedClass(ProductWarehouse.class)
            .buildSessionFactory();

    public static SessionFactory getConnection() {
        return factory;
    }

    public static void closeConnection(){
        factory.close();
    }
}
