package com.tb.backtestservice.service;

import com.tb.backtestservice.model.BackTestMetrics;
import com.tb.backtestservice.model.Trade;

import java.util.ArrayList;
import java.util.List;

public class StrategyService {
    public List<Trade> executeStrategy()
    {
        return new ArrayList<>();
    }
    public BackTestMetrics computeMetrics(List<Trade> trades)
    {
        return new BackTestMetrics();
    }
}
