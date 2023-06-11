package com.tenpo.challenge.facade.percentage;

import com.tenpo.challenge.exceptions.ProviderClientException;
import com.tenpo.challenge.facade.percentage.response.PercentageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PercentageFacadeService {

    private static final String PERCENTAGE_URI = "/percentage";
    private static final String QUERY_STRING = "?sum=";
    private final String providerUrl;

    private final RestTemplate restTemplate;

    public PercentageFacadeService(@Value("${provider.url}") String providerUrl, RestTemplate restTemplate) {
        this.providerUrl = providerUrl;
        this.restTemplate = restTemplate;
    }

    public double getPercentage(double sum) {
        try {
            log.info("Getting percentage from provider.");
            ResponseEntity<PercentageResponse> response = restTemplate.exchange(
                    providerUrl + PERCENTAGE_URI + QUERY_STRING + sum ,
                    HttpMethod.GET,
                    new HttpEntity<>(new HttpHeaders()),
                    PercentageResponse.class);
            return response.getBody().getValue();
        } catch (Exception ex) {
            throw new ProviderClientException();
        }
    }
}
