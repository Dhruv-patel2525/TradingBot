package com.tb.marketdataservice.controller;

import com.tb.marketdataservice.service.MarketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/data")
public class MarketDataController {
    private final MarketService marketService;
    private static final Logger log= LoggerFactory.getLogger(MarketDataController.class);
    public MarketDataController(MarketService marketService)
    {
        this.marketService=marketService;
    }
    @GetMapping
    public ResponseEntity<String>fetchAndPublish(@RequestParam("symbol") String symbol,
                                                 @RequestParam("interval") String interval,
                                                 @RequestParam("start") LocalDate start,
                                                 @RequestParam("end") LocalDate end)
    {
        log.info("{} {} {} {}",symbol,interval,start,end);
        marketService.fetchAndPublishCandles(symbol,interval,start,end);
        return ResponseEntity.ok("Fetch initated");
    }

}
