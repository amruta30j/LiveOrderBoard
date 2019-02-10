package com.silverbarmarketplace.liveorderboard.service;

import com.silverbarmarketplace.liveorderboard.exception.OrderRegistrationException;
import com.silverbarmarketplace.liveorderboard.api.LiveOrdersSummary;
import com.silverbarmarketplace.liveorderboard.api.OrderInformation;

/**
 * Created by amrutaj on 08/02/2019.
 */
public interface OrderProcessor {

    OrderInformation registerOrder(String userId, double orderQuntity, String orderType, Integer orderPrice) throws Exception;

    OrderInformation cancelOrder(Integer OrderId) throws Exception;

    LiveOrdersSummary getOrdersSummary() throws Exception;
}
