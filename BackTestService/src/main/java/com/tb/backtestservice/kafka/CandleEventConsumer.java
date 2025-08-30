package com.tb.backtestservice.kafka;

import com.tb.backtestservice.model.Candle;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CandleEventConsumer {
    @KafkaListener(topics = "candle",groupId = "backtest-service")
    public void consume(Candle event)
    {

    }
}
