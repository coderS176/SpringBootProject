package com.example.journalApp.service;

import com.example.journalApp.api.response.QuoteResponse;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class QuoteService {
    public static final String API = "https://api.api-ninjas.com/v1/quotes?category=best";
    private static final Dotenv dotenv = Dotenv.load();
    @Autowired
    private RestTemplate restTemplate;

    public String getQuote() {
        HttpHeaders headers = new HttpHeaders();
        String apiKey = dotenv.get("Quote_API_KEY");
        headers.set("x-api-key", apiKey);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<List<QuoteResponse>> response = restTemplate.exchange(
                    API, // replace with your actual API URL
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<QuoteResponse>>() {
                    }
            );
            List<QuoteResponse> res = response.getBody();
            if (res != null && !res.isEmpty()) {
                return res.get(0).getQuote();
            } else {
                throw new RuntimeException("No Quote found in the Response.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "No Quote Today.";
        }
    }
}
