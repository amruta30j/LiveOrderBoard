package com.silverbarmarketplace.liveorderboard.repository;

import com.silverbarmarketplace.liveorderboard.model.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by amrutaj on 08/02/2019.
 */
public interface OrderRepository {

    void create(Order order);

    Map<Integer,List<Order>> findAll();

    Order getById(Integer orderId);
}
