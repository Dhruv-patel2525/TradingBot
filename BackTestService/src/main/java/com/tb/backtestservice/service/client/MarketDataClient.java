package com.tb.backtestservice.service.client;

import com.tb.backtestservice.model.Candle;

import java.time.LocalDate;
import java.util.List;

public interface MarketDataClient {

    /**
     * Fetch historical candles from MarketDataService.
     *
     * @param symbol   Symbol name (e.g., BTCUSDT)
     * @param interval Candle interval (1m, 15m, 1h, etc.)
     * @param start    Start date/time of historical candles
     * @param end      End date/time of historical candles
     * @return List of candles
     */
    void fetchCandles(String symbol, String interval, LocalDate start, LocalDate end);
}
