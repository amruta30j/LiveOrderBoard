package com.silverbarmarketplace.liveorderboard.model;

import java.util.UUID;

/**
 * Created by amrutaj on 08/02/2019.
 */
public class Order {

    //required parameters
    private final String userId;
    private final Double quantity;
    private final Integer price;
    private final OrderType type;
    private final Integer orderId;

    //optional order parameters
    //This are the optional parameters and will be set once the order is generated and will be shared with the user as a response
    private OrderStatus status;
    private String description;


    public Order(Integer orderId, String userId, double quantity, Integer price, OrderType type) {
        this.orderId = orderId;
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderType getType() {
        return type;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
