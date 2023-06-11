package com.tenpo.challenge.services.percentage;

import com.tenpo.challenge.facade.percentage.PercentageFacadeService;
import com.tenpo.challenge.services.CalculatorService;
import com.tenpo.challenge.services.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PercentageService implements CalculatorService {

    private final PercentageFacadeService percentageFacadeService;
    private final HistoryService historyService;

    @Override
    public double percentage(double valueOne, double valueTwo) {
        double sum = valueOne + valueTwo;
        double percentage = percentageFacadeService.getPercentage(sum);
        double percentageAmount = sum * (percentage/100);

        historyService.save(valueOne, valueTwo, percentage);

        return sum + percentageAmount;
    }
}
