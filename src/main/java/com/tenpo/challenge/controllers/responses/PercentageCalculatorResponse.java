package com.tenpo.challenge.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PercentageCalculatorResponse {
    private double valueOne;
    private double valueTwo;
    private double result;
}
