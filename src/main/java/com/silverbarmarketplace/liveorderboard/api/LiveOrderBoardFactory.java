package com.silverbarmarketplace.liveorderboard.api;

import com.silverbarmarketplace.liveorderboard.repository.OrderRepository;
import com.silverbarmarketplace.liveorderboard.service.OrderProcessor;

/**
 * Created by amrutaj on 09/02/2019.
 */
// Generate configuration for application
// Returns LiveOrderBoard corresponding to the configuration defined by subclass.

public abstract class LiveOrderBoardFactory {

    abstract protected OrderRepository createOrderRepository();

    abstract protected OrderProcessor createOrderProcessor();

    //Template method design pattern as well as Inversion of control is achieved here as this class delegated the responsibility of object creation to its subclasses.
    //dependency injection is achieved here independent of any framework like spring.
    public final LiveOrderBoard create(){

        return new LiveOrderBoard(createOrderProcessor());
    }

}
