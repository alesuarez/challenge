package com.tenpo.challenge.services.history;

import com.tenpo.challenge.domain.models.Record;
import com.tenpo.challenge.exceptions.NotFoundValueException;
import com.tenpo.challenge.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HistoryStorageServiceTest {

    @Test
    public void saveTest() {
        HistoryRepository historyRepositoryMock = mock(HistoryRepository.class);
        HistoryStorageService historyStorageService = new HistoryStorageService(historyRepositoryMock);

        historyStorageService.save(10.0, 20.0, 30.0);

        verify(historyRepositoryMock, times(1)).save(any(Record.class));
    }

    @Test
    public void getHistory() {
        HistoryRepository historyRepositoryMock = mock(HistoryRepository.class);
        HistoryStorageService historyStorageService = new HistoryStorageService(historyRepositoryMock);
        Pageable pageable = PageRequest.of(0, 10);
        List<Record> records = Arrays.asList(new Record(1, LocalDateTime.of(2023, 6, 11, 18, 45), 1.0 , 2.0, 3.0),
                new Record(2, LocalDateTime.of(2023, 6, 11, 17, 45), 2.0 , 3.0, 4.0));
        Page<Record> recordPage = new PageImpl<>(records);
        when(historyRepositoryMock.findAll(pageable)).thenReturn(recordPage);

        final Page<Record> pages = historyStorageService.getHistory(pageable);

        assertThat(pages.getTotalElements()).isEqualTo(2);
        assertThat(pages.getTotalPages()).isEqualTo(1);
        verify(historyRepositoryMock, times(1)).findAll(pageable);

    }

    @Test
    public void getLastValue_successful() {
        HistoryRepository historyRepositoryMock = mock(HistoryRepository.class);
        HistoryStorageService historyStorageService = new HistoryStorageService(historyRepositoryMock);

        when(historyRepositoryMock.getLastRecordInserted()).thenReturn(Optional.of(Record.builder().build()));

        historyStorageService.getLastValue();

        verify(historyRepositoryMock, times(1)).getLastRecordInserted();
    }

    @Test
    public void getLastValue_whenTheDatabaseIsEmpty_shouldThrowNotFoundValueException() {
        HistoryRepository historyRepositoryMock = mock(HistoryRepository.class);
        HistoryStorageService historyStorageService = new HistoryStorageService(historyRepositoryMock);

        when(historyRepositoryMock.getLastRecordInserted()).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundValueException.class, () -> historyStorageService.getLastValue());

        assertEquals("Cannot provide the value for this operation.", exception.getMessage());
        verify(historyRepositoryMock, times(1)).getLastRecordInserted();
    }
}