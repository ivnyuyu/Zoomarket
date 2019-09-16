package com.ivanyuyuk.dao;

import com.ivanyuyuk.entity.Product;
import com.ivanyuyuk.entity.ProductWarehouse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private SessionFactory factory;

    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.factory = sessionFactory;
    }

    @Override
    public Product getProduct(Long id) {
        Session session = factory.getCurrentSession();
        Product product = null;
        try {
            session.beginTransaction();
            product = session.get(Product.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void saveProduct(Product product) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            ProductWarehouse productWarehouse = new ProductWarehouse(100L, 0L);
            product.setWarehouse(productWarehouse);
            session.save(product);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getProducts() {
        Session session = factory.getCurrentSession();
        List<Product> productList = null;
        try {
            session.beginTransaction();
            Query<Product> theQuery =
                    session.createQuery("select p from Product p",
                            Product.class);
            productList = theQuery.getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void update(Product product, Long id) {
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            Product oldProduct = session.get(Product.class, id);
            oldProduct.setName(product.getName());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setPrice(product.getPrice());
            if (product.getPhoto() != null) {
                oldProduct.setPhoto(product.getPhoto());
            }
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            session.close();
            e.printStackTrace();
        }
    }
}
