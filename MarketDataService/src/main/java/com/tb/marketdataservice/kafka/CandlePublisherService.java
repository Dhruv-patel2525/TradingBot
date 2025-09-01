package com.tb.marketdataservice.kafka;

import com.tb.marketdataservice.model.Candle;
import com.tb.marketdataservice.model.CandleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CandlePublisherService {
    @Autowired
    private KafkaTemplate<String, CandleEvent> kafkaTemplate;
    public void publishCandles(String symbol, String interval, List<Candle>candles)
    {
        CandleEvent candleEvent=new CandleEvent(symbol,interval,candles);
        kafkaTemplate.send("candle",candleEvent);
    }
}
