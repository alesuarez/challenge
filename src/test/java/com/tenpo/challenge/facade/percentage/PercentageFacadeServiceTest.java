package com.tenpo.challenge.facade.percentage;

import com.tenpo.challenge.exceptions.ProviderClientException;
import com.tenpo.challenge.facade.percentage.response.PercentageResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PercentageFacadeServiceTest {

    @Test
    public void getPercentage_whenExternalProviderIsOk_successful() {
        RestTemplate restTemplateMock = mock(RestTemplate.class);
        PercentageFacadeService percentageFacadeService = new PercentageFacadeService("http://mock.test", restTemplateMock);
        ResponseEntity<PercentageResponse> providerResponse = new ResponseEntity<>(new PercentageResponse(10.0), HttpStatus.CREATED);

        when(restTemplateMock.exchange(eq("http://mock.test/percentage?sum=10.0"), eq(HttpMethod.GET), eq(new HttpEntity<>(new HttpHeaders())), eq(PercentageResponse.class)))
                .thenReturn(providerResponse);

        final double percentage = percentageFacadeService.getPercentage(10);

        assertThat(percentage).isEqualTo(10.0);
        verify(restTemplateMock, times(1)).exchange(eq("http://mock.test/percentage?sum=10.0"), eq(HttpMethod.GET), eq(new HttpEntity<>(new HttpHeaders())), eq(PercentageResponse.class));
    }

    @Test
    public void getPercentage_whenExternalProviderFails_shouldThrowProviderClientException() {
        RestTemplate restTemplateMock = mock(RestTemplate.class);
        PercentageFacadeService percentageFacadeService = new PercentageFacadeService("http://mock.test", restTemplateMock);
        doThrow(HttpClientErrorException.class).when(restTemplateMock).exchange(eq("http://mock.test/percentage?sum=10.0"), eq(HttpMethod.GET), eq(new HttpEntity<>(new HttpHeaders())), eq(PercentageResponse.class));

        Exception exception = assertThrows(ProviderClientException.class, () -> percentageFacadeService.getPercentage(10));

        assertEquals("Provider unable to process.", exception.getMessage());
        verify(restTemplateMock, times(1)).exchange(eq("http://mock.test/percentage?sum=10.0"), eq(HttpMethod.GET), eq(new HttpEntity<>(new HttpHeaders())), eq(PercentageResponse.class));
    }
}