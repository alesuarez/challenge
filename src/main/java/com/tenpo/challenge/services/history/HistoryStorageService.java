package com.tenpo.challenge.services.history;

import com.tenpo.challenge.domain.models.Record;
import com.tenpo.challenge.repository.HistoryRepository;
import com.tenpo.challenge.services.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class HistoryStorageService implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public void save(Double numbOne, Double numbTwo, Double numResponse) {
        Record record = Record.builder()
                .numberOne(numbOne)
                .numberTwo(numbTwo)
                .percentageResponse(numResponse)
                .createdDate(LocalDateTime.now())
                .build();

        historyRepository.save(record);
    }

    @Override
    public Page<Record> getHistory(Pageable pageable) {
        return historyRepository.findAll(pageable);
    }
}
