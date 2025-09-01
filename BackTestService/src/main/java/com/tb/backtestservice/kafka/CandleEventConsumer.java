package com.tb.backtestservice.kafka;

import com.tb.backtestservice.model.Candle;
import com.tb.backtestservice.model.CandleEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class CandleEventConsumer {
    private final ConcurrentMap<String, CandleEvent> candleEventStore = new ConcurrentHashMap<>();

    @KafkaListener(topics = "candle", groupId = "backtest-service")
    public void consume(CandleEvent event) {
        String key = event.getSymbol() + "_" + event.getInterval();
        candleEventStore.put(key, event);
        System.out.println("Received CandleEvent: " + event);
    }

    public List<Candle> getCandle(String symbol, String interval) {
        String key = symbol + "_" + interval;
        return candleEventStore.get(key).getCandles();
    }

}
