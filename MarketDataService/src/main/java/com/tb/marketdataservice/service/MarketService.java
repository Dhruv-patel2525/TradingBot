package com.tb.marketdataservice.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.marketdataservice.kafka.CandlePublisherService;
import com.tb.marketdataservice.model.Candle;
import com.tb.marketdataservice.repository.CandleRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MarketService {
    private final CandleRepository candleRepository;
    private final CandlePublisherService candlePublisherService;
    private WebClient webClient;

    public MarketService(CandleRepository candleRepository, CandlePublisherService candlePublisherService) {
        this.candleRepository = candleRepository;
        this.candlePublisherService = candlePublisherService;
        this.webClient= WebClient.builder()
                .baseUrl("https://api.bitget.com")
                .build();    }

    public void fetchAndPublishCandles(String symbol, String interval, LocalDate start,LocalDate end)
    {
        List<Candle> candles=fetchFromBitget(symbol,interval,start,end);
        for(int i=0;i<candles.size();i++)
        {
            System.out.println(candles.get(i));
        }
        candlePublisherService.publishCandles(symbol,interval,candles);
    }
    private List<Candle> fetchFromBitget(String symbol,String interval,LocalDate start,LocalDate end)
    {
        Instant startTime = start.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant endTime = end.atStartOfDay().toInstant(ZoneOffset.UTC);

        String uri = "/api/v2/spot/market/candles?symbol=" + symbol +
                "&granularity=" + interval +
                "&startTime=" + startTime.toEpochMilli() +
                "&endTime=" + endTime.toEpochMilli() +
                "&limit=" + 1000;
        Mono<List<Candle>>mono =  webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .map(this::parseCandles)
                .onErrorResume(e -> {
                    System.out.println("Error calling Bitget API: " + e.getMessage());
                    return Mono.empty(); // prevent .block() from throwing
                });

        return mono
                .onErrorResume(e -> {
                    System.out.println("Failed to fetch candles: " + e.getMessage());
                    return Mono.just(List.of()); // return empty list if API fails
                })
                .block();

//        return new ArrayList<>();
    }

    private List<Candle> parseCandles(String json) {
        ObjectMapper obhectmapper = new ObjectMapper();
        try {
            JsonNode root = obhectmapper.readTree(json);
            JsonNode data = root.path("data");
            List<Candle> candles = new ArrayList<>();
            for (JsonNode arrayNode : data) {
                Candle candle = new Candle();
                candle.setTimestamp(arrayNode.get(0).asLong());
                candle.setOpen(arrayNode.get(1).asDouble());
                candle.setHigh(arrayNode.get(2).asDouble());
                candle.setLow(arrayNode.get(3).asDouble());
                candle.setClose(arrayNode.get(4).asDouble());
                candle.setVolume(arrayNode.get(5).asDouble());
                candles.add(candle);
            }
            return candles;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
