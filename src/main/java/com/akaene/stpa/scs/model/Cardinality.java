package com.akaene.stpa.scs.model;

public enum Cardinality {
    ZERO("0"), ONE("1"), ANY("*");

    private final String symbol;


    Cardinality(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
