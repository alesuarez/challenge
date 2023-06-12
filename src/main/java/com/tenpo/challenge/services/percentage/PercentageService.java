package com.tenpo.challenge.services.percentage;

import com.tenpo.challenge.facade.percentage.PercentageFacadeService;
import com.tenpo.challenge.services.CalculatorService;
import com.tenpo.challenge.services.HistoryService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(
            name = "percentageProvider",
            fallbackMethod = "obtainLastPercentageFromDatabase")
    public double percentage(double valueOne, double valueTwo) {

        double percentage = percentageFacadeService.getPercentage(valueOne + valueTwo);

        historyService.save(valueOne, valueTwo, percentage);

        return calculatePercentage(valueOne, valueTwo, percentage);
    }

    private double calculatePercentage(double valueOne, double valueTwo, double percentage) {
        double sum = valueOne + valueTwo;
        double percentageAmount = sum * (percentage/100);
        return sum + percentageAmount;
    }

    private double obtainLastPercentageFromDatabase(double valueOne, double valueTwo, Throwable t) {
        log.info("Something was wrong with percentage service");
        double percentageDb = historyService.getLastValue().getPercentageResponse();

        return calculatePercentage(valueOne, valueTwo, percentageDb);
    }
}
