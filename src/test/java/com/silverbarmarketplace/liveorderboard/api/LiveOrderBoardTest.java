package com.silverbarmarketplace.liveorderboard.api;

import com.silverbarmarketplace.liveorderboard.exception.OrderCancellationException;
import com.silverbarmarketplace.liveorderboard.exception.OrderRegistrationException;
import com.silverbarmarketplace.liveorderboard.model.Order;
import com.silverbarmarketplace.liveorderboard.model.OrderStatus;
import com.silverbarmarketplace.liveorderboard.model.OrderType;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;

/**
 * Created by amrutaj on 10/02/2019.
 */
public class LiveOrderBoardTest {

    private LiveOrderBoardFactory liveOrderBoardFactory = new LiveOrderBoardInMemoryDBFactory();

    private LiveOrderBoard liveOrderBoard = liveOrderBoardFactory.create();

    private final Map<Integer, Double> liveSellOrderSummary = new TreeMap<>();

    final Map<Integer, Double> liveBuyOrderSummary = new TreeMap<>();



    @Before
    public void setUp() throws Exception {

        liveSellOrderSummary.put(300,3.8);
        liveSellOrderSummary.put(305,7.0);
        liveSellOrderSummary.put(310,3.5);
        liveSellOrderSummary.put(400,6.4);
        liveSellOrderSummary.put(500,1.4);

        liveBuyOrderSummary.put(500,4.4);
        liveBuyOrderSummary.put(400,7.199999999999999);
        liveBuyOrderSummary.put(310,3.5);
    }

    @Test
    public void createOrder() throws Exception {

        OrderInformation information =liveOrderBoard.createOrder("user1", 3.2, "buy", 300);
        assertNotNull(information);
        assertTrue(information.getOrderStatus().equals(OrderStatus.REGISTERED));
    }

    @Test(expected = OrderRegistrationException.class)
    public void createOrderFailure() throws Exception{
        OrderInformation information =liveOrderBoard.createOrder("user1", 3.2, null, null);
        assertNotNull(information);
        assertTrue(information.getOrderStatus().equals(OrderStatus.FAILED));
    }

    @Test
    public void cancelOrder() throws Exception {
        OrderInformation informationCreate = liveOrderBoard.createOrder("user1", 3.2, "buy", 300);
        OrderInformation informationCancel =liveOrderBoard.cancelOrder(informationCreate.getOrderId());
        assertNotNull(informationCancel);
        assertTrue(informationCancel.getOrderStatus().equals(OrderStatus.CANCELLED));
    }

    @Test(expected = OrderCancellationException.class)
    public void cancelOrderFailure() throws Exception {
        OrderInformation information =liveOrderBoard.cancelOrder(3);
        assertNotNull(information);
        assertTrue(information.getOrderStatus().equals(OrderStatus.CANCELLED));
    }

    @Test
    public void getLiveOrdersSummary() throws Exception {

        liveOrderBoard.createOrder("XXX", 3.4,"buy",400);
        liveOrderBoard.createOrder("XX1", 2.4,"buy",400);
        liveOrderBoard.createOrder("XX1", 1.4,"buy",400);
        liveOrderBoard.createOrder("XX2", 4.4,"buy",500);
        liveOrderBoard.createOrder("XX3", 1.4,"sell",500);
        liveOrderBoard.createOrder("XX4", 6.4,"sell",400);
        liveOrderBoard.createOrder("XX5", 1.4,"sell",300);
        liveOrderBoard.createOrder("XX6", 2.4,"sell",300);
        liveOrderBoard.createOrder("XXX", 3.5, "sell", 305);
        liveOrderBoard.createOrder("XXX", 3.5, "sell", 305);
        liveOrderBoard.createOrder("XXX", 3.5, "sell", 310);
        liveOrderBoard.createOrder("XXX", 3.5, "buy", 310);

        LiveOrdersSummary liveOrdersSummary = liveOrderBoard.getLiveOrdersSummary();
        liveOrdersSummary.print();
        assertNotNull(liveOrdersSummary);
        assertEquals(liveSellOrderSummary, liveOrdersSummary.getLiveSellOrderSummary());
        assertEquals(liveBuyOrderSummary, liveOrdersSummary.getLiveBuyOrderSummary());

    }

    @After
    public void tearDown() throws Exception {

        liveOrderBoardFactory = null;
        liveOrderBoard=null;
        liveBuyOrderSummary.clear();
        liveSellOrderSummary.clear();

    }
}