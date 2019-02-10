package com.silverbarmarketplace.liveorderboard.api;

import com.silverbarmarketplace.liveorderboard.repository.OrderRepository;
import com.silverbarmarketplace.liveorderboard.repository.OrderRepositoryImpl;
import com.silverbarmarketplace.liveorderboard.service.OrderProcessor;
import com.silverbarmarketplace.liveorderboard.service.OrderProcessorImpl;

/**
 * Created by amrutaj on 09/02/2019.
 */
//LiveOrderBoard created using this class configuration uses in-memory database.
public class LiveOrderBoardInMemoryDBFactory extends LiveOrderBoardFactory {

    @Override
    protected OrderRepository createOrderRepository() {
        return new OrderRepositoryImpl();
    }

    @Override
    protected OrderProcessor createOrderProcessor() {
        return new OrderProcessorImpl(createOrderRepository());
    }
}
