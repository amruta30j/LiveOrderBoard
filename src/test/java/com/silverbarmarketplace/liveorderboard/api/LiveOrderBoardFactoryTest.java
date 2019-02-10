package com.silverbarmarketplace.liveorderboard.api;

import com.silverbarmarketplace.liveorderboard.repository.OrderRepository;
import com.silverbarmarketplace.liveorderboard.service.OrderProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by amrutaj on 10/02/2019.
 */
public class LiveOrderBoardFactoryTest {

    private LiveOrderBoardFactory liveOrderBoardFactory;

    @Before
    public void setUp() throws Exception {

        liveOrderBoardFactory = new LiveOrderBoardInMemoryDBFactory();

    }

    @Test
    public void createOrderRepository() throws Exception {
        assertTrue(liveOrderBoardFactory.createOrderRepository() instanceof OrderRepository);
        assertNotNull(liveOrderBoardFactory.createOrderRepository());
    }

    @Test
    public void createOrderProcessor() throws Exception {
        assertTrue(liveOrderBoardFactory.createOrderProcessor() instanceof OrderProcessor);
        assertNotNull(liveOrderBoardFactory.createOrderProcessor());
    }

    @Test
    public void create() throws Exception {
        assertTrue(liveOrderBoardFactory.create() instanceof LiveOrderBoard);
        assertNotNull(liveOrderBoardFactory.create());
    }

    @After
    public void tearDown() throws Exception {
        liveOrderBoardFactory =null;
    }
}