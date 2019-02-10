package com.silverbarmarketplace.liveorderboard.repository;

import com.silverbarmarketplace.liveorderboard.model.Order;
import com.silverbarmarketplace.liveorderboard.model.OrderType;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by amrutaj on 10/02/2019.
 */
public class OrderRepositoryTest {

    private OrderRepository repository;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Before
    public void setUp() throws Exception {

        repository = new OrderRepositoryImpl();
    }

    @Test
    public void create() throws Exception {
        repository.create(new Order(1,"xxx",3.3,200, OrderType.SELL));
        repository.create(new Order(2,"xxx",3.3,200, OrderType.BUY));
        assertNotNull(repository.findAll());
    }

    @Test(expected = NullPointerException.class)
    public void createFailure(){
        repository.create(new Order(1,"xxx",3.3,null, null));
        assertNull(repository.findAll());
    }


    @Test
    public void findAll() throws Exception {
        repository.create(new Order(1,"xxx",3.3,200, OrderType.SELL));
        repository.create(new Order(2,"xxx",3.3,200, OrderType.BUY));
        repository.create(new Order(3,"xxx",3.3,201, OrderType.SELL));
        repository.create(new Order(4,"xxx",3.3,201, OrderType.BUY));
        repository.create(new Order(5,"xxx",3.3,202, OrderType.SELL));
        repository.create(new Order(6,"xxx",3.3,202, OrderType.BUY));
        assertNotNull(repository.findAll());
    }

    @Test
    public void getById() throws Exception {
        repository.create(new Order(1,"xxx",3.3,200, OrderType.SELL));
        assertNotNull(repository.getById(1));
    }

}