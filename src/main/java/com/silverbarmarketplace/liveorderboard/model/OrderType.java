package com.silverbarmarketplace.liveorderboard.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by amrutaj on 08/02/2019.
 */
public enum OrderType {

    SELL("sell","Sell"),
    BUY("buy","Buy");

    private final String orderType;

    private final String orderTypeDesc;

    OrderType(final String orderType, final String orderTypeDesc) {
        this.orderType = orderType;
        this.orderTypeDesc = orderTypeDesc;
    }

    private static final Map<String, OrderType> lookup = new HashMap<>();

    // populate the lookup table on loading time
    static {
        for (OrderType orderTypes : EnumSet.allOf(OrderType.class)) {
            lookup.put(orderTypes.orderType,orderTypes);
        }
    }
    public static OrderType get( String orderType) {
        return lookup.get(orderType);
    }




}
