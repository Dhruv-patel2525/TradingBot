package com.tb.backtestservice.controller;

import com.tb.backtestservice.dto.BackTestResponse;
import com.tb.backtestservice.service.BackTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/backtest")
public class BackTestController {
    private final BackTestService backTestService;
    public BackTestController(BackTestService backTestService)
    {
        this.backTestService=backTestService;
    }
    @GetMapping("/run")
    public ResponseEntity<BackTestResponse>runBackTest(@RequestParam("symbol") String symbol,
                                                       @RequestParam("interval") String interval,
                                                       @RequestParam("start") LocalDate start,
                                                       @RequestParam("end") LocalDate end)
    {
        System.out.println("Symbol "+symbol+" interval "+interval+" start "+start+" end "+end);
        BackTestResponse backTestResult=backTestService.runBackTest(symbol,interval,start,end);
        return ResponseEntity.ok(backTestResult);

    }

    @GetMapping("/test")
    public ResponseEntity<String> testController()
    {
        System.out.println("test contoller");
        return ResponseEntity.ok("test link");
    }
}
