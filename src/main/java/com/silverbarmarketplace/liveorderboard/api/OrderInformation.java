package com.silverbarmarketplace.liveorderboard.api;

/**
 * Created by amrutaj on 08/02/2019.
 */

import com.silverbarmarketplace.liveorderboard.model.OrderStatus;
import com.silverbarmarketplace.liveorderboard.model.OrderType;

import java.util.UUID;

/** This class is a domain class which
 *  we will be returned to the user as a response
 *  of createOrder functionality
 */

public class OrderInformation {

    private final Integer orderId;

    private final OrderStatus orderStatus;

    public OrderInformation(Integer orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
