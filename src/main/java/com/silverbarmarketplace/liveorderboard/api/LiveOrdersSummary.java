package com.silverbarmarketplace.liveorderboard.api;

import com.silverbarmarketplace.liveorderboard.model.OrderType;

import java.util.Map;

/**
 * Created by amrutaj on 08/02/2019.
 */
public class LiveOrdersSummary {

    final Map<Integer, Double> liveSellOrderSummary;

    final Map<Integer, Double> liveBuyOrderSummary;

    public LiveOrdersSummary(Map<Integer, Double> liveSellOrderSummary, Map<Integer, Double> liveBuyOrderSummary) {
        this.liveSellOrderSummary = liveSellOrderSummary;
        this.liveBuyOrderSummary = liveBuyOrderSummary;
    }

    public Map<Integer, Double> getLiveSellOrderSummary() {
        return liveSellOrderSummary;
    }

    public Map<Integer, Double> getLiveBuyOrderSummary() {
        return liveBuyOrderSummary;
    }

    public void print(){
        liveSellOrderSummary.forEach((k, v) -> System.out.println((OrderType.SELL +": "+v+" kg for "+"£"+k)));
        liveBuyOrderSummary.forEach((k, v) -> System.out.println((OrderType.BUY +": "+v+" kg for "+"£"+k)));
    }
}
