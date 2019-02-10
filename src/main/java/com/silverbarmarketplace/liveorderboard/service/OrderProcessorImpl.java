package com.silverbarmarketplace.liveorderboard.service;

import com.silverbarmarketplace.liveorderboard.api.LiveOrdersSummary;
import com.silverbarmarketplace.liveorderboard.api.OrderInformation;
import com.silverbarmarketplace.liveorderboard.exception.OrderCancellationException;
import com.silverbarmarketplace.liveorderboard.exception.OrderRegistrationException;
import com.silverbarmarketplace.liveorderboard.model.*;
import com.silverbarmarketplace.liveorderboard.repository.OrderRepository;

import java.util.*;

/**
 * Created by amrutaj on 08/02/2019.
 */
public class OrderProcessorImpl implements OrderProcessor {

    private OrderRepository orderRepository;

    private static Integer nextOrderId = 1;

    private static Integer getNextOrderId() {
        return nextOrderId++;
    }

    public OrderProcessorImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderInformation registerOrder(String userId, double orderQuantity, String orderType, Integer orderPrice) throws Exception {
        Order order = new Order(getNextOrderId(), userId, orderQuantity, orderPrice, OrderType.get(orderType));
        try {
            orderRepository.create(order);
            order.setStatus(OrderStatus.REGISTERED);
        } catch (Exception e) {
            order.setStatus(OrderStatus.FAILED);
            throw e;
        }

        OrderInformation orderInformation = new OrderInformation(order.getOrderId(), order.getStatus());
        return orderInformation;
    }

    public OrderInformation cancelOrder(Integer orderId) throws Exception {

        try {
            Order order = orderRepository.getById(orderId);
            order.setStatus(OrderStatus.CANCELLED);
            OrderInformation orderInformation = new OrderInformation(order.getOrderId(), order.getStatus());
            return orderInformation;
        } catch (Exception e) {
            throw e;
        }
    }

    public LiveOrdersSummary getOrdersSummary() throws Exception {
        try {
            Map<Integer, List<Order>> orders = orderRepository.findAll();

            Map<Integer, Double> sellOrdersSummary = new TreeMap<>();
            Map<Integer, Double> buyOrdersSummary = new TreeMap<>(Collections.reverseOrder());
            double buyQuantity, sellQuantity;

            Set<Integer> prices = orders.keySet();
            for (Integer price : prices) {

                sellQuantity = orders.get(price).stream()
                        .filter(o -> o.getType().equals(OrderType.SELL))
                        .filter(o -> o.getStatus().equals(OrderStatus.REGISTERED))
                        .mapToDouble(Order::getQuantity).sum();
                if (sellQuantity != 0)
                    sellOrdersSummary.put(price, sellQuantity);

                buyQuantity = orders.get(price).stream()
                        .filter(o -> o.getType().equals(OrderType.BUY))
                        .filter(o -> o.getStatus().equals(OrderStatus.REGISTERED))
                        .mapToDouble(Order::getQuantity).sum();
                if (buyQuantity != 0)
                    buyOrdersSummary.put(price, buyQuantity);

            }
            LiveOrdersSummary liveOrdersSummary = new LiveOrdersSummary(sellOrdersSummary, buyOrdersSummary);
            return liveOrdersSummary;
        } catch (Exception e) {
            throw e;
        }
    }

}
