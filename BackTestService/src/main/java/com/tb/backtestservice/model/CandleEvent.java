package com.tb.backtestservice.model;

import java.util.List;

public class CandleEvent {
    public CandleEvent(String symbol, String interval, List<Candle> candles) {
        this.symbol = symbol;
        this.interval = interval;
        this.candles = candles;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public List<Candle> getCandles() {
        return candles;
    }

    public void setCandles(List<Candle> candles) {
        this.candles = candles;
    }

    private String symbol;
    private String interval;
    private List<Candle>candles;//batch of data

    @Override
    public String toString() {
        return "CandleEvent{" +
                "symbol='" + symbol + '\'' +
                ", interval='" + interval + '\'' +
                ", candles=" + candles +
                '}';
    }
}
