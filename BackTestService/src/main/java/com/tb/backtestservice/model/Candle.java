package com.tb.backtestservice.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "candles")
public class Candle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private long timestamp;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;

}
