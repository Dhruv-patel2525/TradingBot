package com.tb.backtestservice.service;

import com.tb.backtestservice.dto.BackTestRequest;
import com.tb.backtestservice.dto.BackTestResponse;
import com.tb.backtestservice.model.BackTestMetrics;
import com.tb.backtestservice.model.Candle;
import com.tb.backtestservice.model.Trade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackTestService {
    private final StrategyService strategyService;
    public BackTestService(StrategyService strategyService)
    {
        this.strategyService=strategyService;
    }
    public BackTestResponse runBackTest(BackTestRequest request)
    {
        List<Candle>candles=new ArrayList<>();
//      candles=candleCache.getCandles(request.getSymbol(),request.getInterval(),request.getStartTime(),request.getEndTime(),request.getInterval())
         if(candles.isEmpty())
         {
             //Call another microservice
         }
         List<Trade>trades=strategyService.executeStrategy();
         BackTestMetrics metrics=strategyService.computeMetrics(trades);


//        return new BackTestResponse();
    }
}
