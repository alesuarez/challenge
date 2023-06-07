package com.tenpo.challenge.services.percentage;

import com.tenpo.challenge.facade.percentage.PercentageFacadeService;
import com.tenpo.challenge.services.CalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PercentageService implements CalculatorService {

    private final PercentageFacadeService percentageFacadeService;
    public double percentage(double valueOne, double valueTwo) {
        double sum = valueOne + valueTwo;
        double percentage = sum * (percentageFacadeService.getPercentage(sum)/100);
        return sum + percentage;
    }
}
