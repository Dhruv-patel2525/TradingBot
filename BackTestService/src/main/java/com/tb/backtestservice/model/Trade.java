package com.tb.backtestservice.model;

import java.time.LocalDateTime;

public class Trade {
    private LocalDateTime entryTime;
    private String positionType;//LONG SHORT
    private double entryPrice;
    private double exitPrice;
    private LocalDateTime  exitTime;
    private double profitLoss;
}
