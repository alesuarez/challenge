package com.tenpo.challenge.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PercentageCalculatorRequest {
    private double valueOne;
    private double valueTwo;
}
