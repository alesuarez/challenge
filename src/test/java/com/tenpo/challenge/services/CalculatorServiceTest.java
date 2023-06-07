package com.tenpo.challenge.services;

import com.tenpo.challenge.facade.percentage.PercentageFacadeService;
import com.tenpo.challenge.services.percentage.PercentageService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalculatorServiceTest {

    @Test
    public void calculatePercentageTest() {
        // @Given
        PercentageFacadeService percentageFacadeService = mock(PercentageFacadeService.class);
        CalculatorService percentageService = new PercentageService(percentageFacadeService);

        when(percentageFacadeService.getPercentage(30)).thenReturn(10.00);

        final double percentage = percentageService.percentage(10, 20);

        // @When
        // @Then
        assertThat(percentage).isEqualTo(33);
    }
}