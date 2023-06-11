package com.tenpo.challenge.services.percentage;

import com.tenpo.challenge.facade.percentage.PercentageFacadeService;
import com.tenpo.challenge.services.CalculatorService;
import com.tenpo.challenge.services.HistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PercentageService implements CalculatorService {

    private final PercentageFacadeService percentageFacadeService;
    private final HistoryService historyService;

    @Override
    public double percentage(double valueOne, double valueTwo) {
        double sum = valueOne + valueTwo;
        double percentage = getPercentage(sum);
        double percentageAmount = sum * (percentage/100);

        historyService.save(valueOne, valueTwo, percentage);

        return sum + percentageAmount;
    }

    private double getPercentage(double sum) {
        try {
            return percentageFacadeService.getPercentage(sum);
        } catch (Exception e) {
            log.info("Something was wrong with percentage service");
            return historyService.getLastValue().getPercentageResponse();
        }
    }
}
