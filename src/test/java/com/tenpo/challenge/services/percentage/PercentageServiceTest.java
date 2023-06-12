package com.tenpo.challenge.services.percentage;

import com.tenpo.challenge.facade.percentage.PercentageFacadeService;
import com.tenpo.challenge.services.CalculatorService;
import com.tenpo.challenge.services.HistoryService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PercentageServiceTest {

    @Test
    public void calculatePercentageTest_successful() {
        // @Given
        PercentageFacadeService percentageFacadeServiceMock = mock(PercentageFacadeService.class);
        HistoryService historyServiceMock = mock(HistoryService.class);
        CalculatorService percentageService = new PercentageService(percentageFacadeServiceMock, historyServiceMock);

        when(percentageFacadeServiceMock.getPercentage(30.0)).thenReturn(10.0);
        doNothing().when(historyServiceMock).save(10.0, 20.0, 10.0);

        final double percentage = percentageService.percentage(10.0, 20.0);

        // @When
        // @Then
        assertThat(percentage).isEqualTo(33.0);

        verify(percentageFacadeServiceMock, times(1)).getPercentage(30.0);
        verify(historyServiceMock, times(1)).save(10.0, 20.0, 10.0);
    }
}