package com.tenpo.challenge.services.history;

import com.tenpo.challenge.domain.models.Record;
import com.tenpo.challenge.exceptions.NotFoundValueException;
import com.tenpo.challenge.repository.HistoryRepository;
import com.tenpo.challenge.services.HistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryStorageService implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    @Async("saveHistory")
    public void save(Double numbOne, Double numbTwo, Double numResponse) {
        log.info("Saving on database...");
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

    @Override
    public Record getLastValue() {
        log.info("Getting the last known value from database.");
        return historyRepository.getLastRecordInserted().orElseThrow(NotFoundValueException::new);
    }
}
