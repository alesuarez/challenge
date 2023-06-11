package com.tenpo.challenge.controllers;

import com.tenpo.challenge.domain.models.Record;
import com.tenpo.challenge.services.HistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HistoryController.class)
public class HistoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private HistoryService historyService;

    @Test
    public void getAll() throws Exception {

        List<Record> records = Arrays.asList(new Record(1, LocalDateTime.of(2023, 6, 11, 18, 45), 1.0 , 2.0, 3.0),
                new Record(2, LocalDateTime.of(2023, 6, 11, 17, 45), 2.0 , 3.0, 4.0));

        Page<Record> recordPage = new PageImpl<>(records);

        when(historyService.getHistory(PageRequest.of(0, 10))).thenReturn(recordPage);

        mvc.perform(get("/history?page=0&size=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"content\":[{\"id\":1,\"createdDate\":\"2023-06-11T18:45:00\",\"numberOne\":1.0,\"numberTwo\":2.0,\"percentageResponse\":3.0},{\"id\":2,\"createdDate\":\"2023-06-11T17:45:00\",\"numberOne\":2.0,\"numberTwo\":3.0,\"percentageResponse\":4.0}],\"pageable\":\"INSTANCE\",\"last\":true,\"totalElements\":2,\"totalPages\":1,\"number\":0,\"size\":2,\"sort\":{\"empty\":true,\"sorted\":false,\"unsorted\":true},\"first\":true,\"numberOfElements\":2,\"empty\":false}"));
    }
}