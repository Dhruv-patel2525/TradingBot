package com.tb.backtestservice.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "backtest_results")
public class BackTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String symbol;
    private String strategyName;
    private String interval;
    private LocalDate startDate;
    private LocalDate endDate;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Trade> trades;

    @Embedded
    private BackTestMetrics metrics;

}
