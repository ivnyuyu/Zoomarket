package com.ivanyuyuk.dao;

import com.ivanyuyuk.entity.Order;
import com.ivanyuyuk.entity.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class OrderDaoImpl implements OrderDao  {
    private SessionFactory factory;

    public OrderDaoImpl(SessionFactory sessionFactory){
        this.factory=sessionFactory;
    }

    @Override
    public void saveOrder(Order order, Set<Long> productList) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            for (Long temp : productList) {
                Product product = session.get(Product.class, temp);
                order.addNewProduct(product);
            }
            session.save(order);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrders() {
        Session session = factory.getCurrentSession();
        List<Order> orders = null;
        try {
            session.beginTransaction();
            Query<Order> theQuery =
                    session.createQuery("select p from Order p",
                            Order.class);
            orders = theQuery.getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void markOrderIsComplete(Long id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Order order = session.get(Order.class, id);
            order.setActive(false);
            for (int i = 0; i < order.getProductList().size(); i++) {
                order.getProductList().get(i).getWarehouse().addSold(1L);
                order.getProductList().get(i).getWarehouse().decreaseAvailable(1L);
                System.out.println(order.getProductList().get(i).getWarehouse().getAvailable());
                System.out.println(order.getProductList().get(i).getWarehouse().getSold());
                session.saveOrUpdate(order.getProductList().get(i).getWarehouse());
            }
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
    }

    @Override
    public void setCountProduct(Long id, Long value) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Product product=session.get(Product.class,id);
            product.getWarehouse().setAvailable(value);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
    }
}
