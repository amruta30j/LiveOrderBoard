package com.silverbarmarketplace.liveorderboard.api;

import com.silverbarmarketplace.liveorderboard.exception.OrderCancellationException;
import com.silverbarmarketplace.liveorderboard.exception.OrderRegistrationException;
import com.silverbarmarketplace.liveorderboard.exception.OrderRetrievalException;
import com.silverbarmarketplace.liveorderboard.repository.OrderRepositoryImpl;
import com.silverbarmarketplace.liveorderboard.service.OrderProcessor;
import com.silverbarmarketplace.liveorderboard.service.OrderProcessorImpl;

/**
 * Created by amrutaj on 08/02/2019.
 */
public class LiveOrderBoard {

    private OrderProcessor orderProcessor;

    public LiveOrderBoard(OrderProcessor processor) {
        this.orderProcessor = processor;
    }


    public OrderInformation createOrder(String userId, double orderQuantity, String orderType, Integer orderPrice) throws OrderRegistrationException {
        try {

            OrderInformation info = orderProcessor.registerOrder(userId, orderQuantity, orderType, orderPrice);
            return info;
        } catch (Exception ex) {
            throw new OrderRegistrationException("Failed to register the order");
        }
    }


    public OrderInformation cancelOrder(Integer orderId) throws OrderCancellationException {
        try {
            OrderInformation info = orderProcessor.cancelOrder(orderId);
            return info;
        } catch (Exception ex) {
            throw new OrderCancellationException("Failed to cancel the order");
        }
    }

    public LiveOrdersSummary getLiveOrdersSummary() throws OrderRetrievalException{
        try {
            LiveOrdersSummary liveOrdersSummary = orderProcessor.getOrdersSummary();
            return liveOrdersSummary;
        } catch (Exception ex){
            throw new OrderRetrievalException("Error while fetching the live orders");
        }
    }
}
