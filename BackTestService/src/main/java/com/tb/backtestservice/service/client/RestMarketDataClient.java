package com.tb.backtestservice.service.client;

import com.tb.backtestservice.model.Candle;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class RestMarketDataClient implements MarketDataClient {
    private final WebClient webClient;
    public RestMarketDataClient(WebClient.Builder webClientBuilder)
    {
        this.webClient = webClientBuilder.baseUrl("http://marketdata-service:6002").build();
    }
    @Override
    public void fetchCandles(String symbol, String interval, LocalDate start, LocalDate end) {

              webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .path("/data")
                                .queryParam("symbol", symbol)
                                .queryParam("interval", interval)
                                .queryParam("start", start)
                                .queryParam("end", end)
                                .build())
                        .retrieve()
                        .bodyToMono(Void.class)
                        .block(); // blocking call for simplicity in MVP


    }
}
