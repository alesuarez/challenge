package com.tenpo.challenge.services;

import com.tenpo.challenge.domain.models.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HistoryService {
    void save(Double numbOne, Double numbTwo, Double numResponse);
    Page<Record> getHistory(Pageable pageable);
}
