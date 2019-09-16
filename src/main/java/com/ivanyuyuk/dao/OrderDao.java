package com.ivanyuyuk.dao;

import com.ivanyuyuk.entity.Order;

import java.util.List;
import java.util.Set;

public interface OrderDao {
    void saveOrder(Order order, Set<Long> productList);

    List<Order> getOrders();

    void markOrderIsComplete(Long id);

    void setCountProduct(Long id, Long value);
}
