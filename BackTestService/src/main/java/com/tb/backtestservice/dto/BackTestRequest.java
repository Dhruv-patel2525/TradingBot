package com.tb.backtestservice.dto;

import java.time.LocalDate;

public class BackTestRequest {
    private String symbol;
    private String strategyName;
    private String interval;
    private LocalDate startTime;
    private LocalDate endTime;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }
}

//private String symbol;
//private String strategyName;
//private StrategyParams strategyParams;
//private String interval;
//private LocalDate startDate;
//private LocalDate endDate;
