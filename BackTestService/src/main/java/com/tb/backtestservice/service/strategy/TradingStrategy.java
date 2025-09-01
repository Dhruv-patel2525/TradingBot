package com.tb.backtestservice.service.strategy;

import com.tb.backtestservice.model.BackTestMetrics;
import com.tb.backtestservice.model.Candle;
import com.tb.backtestservice.model.Trade;

import java.util.List;

public interface TradingStrategy {
    List<Trade> execute(List<Candle>candles);
    BackTestMetrics computeMetrics(List<Trade>trades);
    String getName();
}
