package com.tenpo.challenge.controllers;

import com.tenpo.challenge.controllers.request.PercentageCalculatorRequest;
import com.tenpo.challenge.controllers.responses.PercentageCalculatorResponse;
import com.tenpo.challenge.exceptions.LimitRequestException;
import com.tenpo.challenge.services.CalculatorService;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@AllArgsConstructor
public class ChallengeController {

    private final CalculatorService calculatorService;

    @PostMapping("/percentage")
    @RateLimiter(name="calculatorService", fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<PercentageCalculatorResponse> getPercentage(@RequestBody PercentageCalculatorRequest percentageCalculatorRequest) {
        double result = calculatorService.percentage(percentageCalculatorRequest.getValueOne(), percentageCalculatorRequest.getValueTwo());

        PercentageCalculatorResponse percentageCalculatorResponse = PercentageCalculatorResponse.builder()
                .valueOne(percentageCalculatorRequest.getValueOne())
                .valueTwo(percentageCalculatorRequest.getValueTwo())
                .result(result)
                .build();

        return new ResponseEntity<>(percentageCalculatorResponse, HttpStatus.OK);
    }

    public ResponseEntity<String> rateLimiterFallback(RequestNotPermitted e){
        throw new LimitRequestException();
    }
}
