package com.tb.backtestservice.service;

import com.tb.backtestservice.dto.BackTestRequest;
import com.tb.backtestservice.dto.BackTestResponse;
import com.tb.backtestservice.kafka.CandleEventConsumer;
import com.tb.backtestservice.model.BackTestMetrics;
import com.tb.backtestservice.model.Candle;
import com.tb.backtestservice.model.Trade;
import com.tb.backtestservice.service.client.MarketDataClient;
import com.tb.backtestservice.service.client.RestMarketDataClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BackTestService {
    private final StrategyService strategyService;
    private final MarketDataClient marketDataClient;
    private final CandleEventConsumer candleEventConsumer;
    public BackTestService(StrategyService strategyService, RestMarketDataClient restMarketDataClient,CandleEventConsumer candleEventConsumer)
    {
        this.strategyService=strategyService;
        this.marketDataClient=restMarketDataClient;
        this.candleEventConsumer=candleEventConsumer;
    }
    public BackTestResponse runBackTest(String symbol, String interval, LocalDate start, LocalDate end)
    {
        List<Candle>candles=new ArrayList<>();
//      candles=candleCache.getCandles(request.getSymbol(),request.getInterval(),request.getStartTime(),request.getEndTime(),request.getInterval())
         if(candles.isEmpty())
         {
             marketDataClient.fetchCandles( symbol, interval, start, end);
             candles= candleEventConsumer.getCandle(symbol, interval);
//             for(int i=0;i<candles.size();i++)
//             {
//                 System.out.println(candles.get(i));
//             }
         }
         System.out.println("Test");
         List<Trade>trades=strategyService.executeStrategy();
         BackTestMetrics metrics=strategyService.computeMetrics(trades);
         return new BackTestResponse();
//        return new BackTestResponse();
    }
}
