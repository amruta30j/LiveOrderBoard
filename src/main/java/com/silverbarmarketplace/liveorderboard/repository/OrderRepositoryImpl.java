package com.silverbarmarketplace.liveorderboard.repository;

import com.silverbarmarketplace.liveorderboard.model.*;

import java.util.*;

/**
 * Created by amrutaj on 08/02/2019.
 */
public class OrderRepositoryImpl implements OrderRepository {

    public OrderRepositoryImpl() {
    }

    //This is in-memory persistence unit for the orders.
    // K-- Key is Order price
    // V -- Value is List of orders
    Map<Integer, List<Order>> orderByPriceMap = new TreeMap<Integer, List<Order>>();

    //This map contains the createdOrders and their respective system generated unique orderIds
    // K -- Key -- UniqueOrderId
    //V -- Value -- Registered order
    Map<Integer, Order> orderByIdMap = new HashMap<Integer, Order>();

    /** This function registers the order submitted by the user.
     *  In the response this function returns the information of the newly created order.
     *  User thereafter can use this Information to cancel or for the further records.
     * @param order
     * @return
     */
    public void create(Order order) {
        List<Order> orders;
            orders = orderByPriceMap.get(order.getPrice());
            if (orders == null) {
                orders = new ArrayList<>();
                orderByPriceMap.put(order.getPrice(), orders);
            }
            orders.add(order);
            
            orderByIdMap.put(order.getOrderId(), order);
    }

    public Map<Integer, List<Order>> findAll() {
        return orderByPriceMap;

    }

    public Order getById(Integer orderId) {
            return orderByIdMap.get(orderId);
    }
}
