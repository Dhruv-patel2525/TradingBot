package com.tb.marketdataservice.repository;

import com.tb.marketdataservice.model.Candle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CandleRepository extends JpaRepository<Candle, UUID> {

}
