package com.tb.backtestservice.controller;

import com.tb.backtestservice.dto.BackTestRequest;
import com.tb.backtestservice.dto.BackTestResponse;
import com.tb.backtestservice.service.BackTestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backtest")
public class BackTestController {
    private final BackTestService backTestService;
    public BackTestController(BackTestService backTestService)
    {
        this.backTestService=backTestService;
    }
    @PostMapping("/run")
    public ResponseEntity<BackTestResponse>runBackTest(@RequestBody BackTestRequest request)
    {
        BackTestResponse backTestResult=backTestService.runBackTest(request);
        return ResponseEntity.ok(backTestResult);

    }
}
