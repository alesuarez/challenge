package com.tenpo.challenge.controllers;

import com.tenpo.challenge.services.percentage.PercentageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PercentageService calculatorService;

    @Test
    public void getPercentage() throws Exception {
        when(calculatorService.percentage(123,321)).thenReturn(456.00);

        mvc.perform(post("/calculator/percentage")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"valueOne\":123.0,\"valueTwo\":321.0}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"valueOne\":123.0,\"valueTwo\":321.0,\"result\":456.0}"));
    }
}