package com.example.journalApp.service;

import com.example.journalApp.api.response.QouteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Component
public class QouteService {
    public static final String API = "https://codeforces.com/api/contest.ratingChanges?contestId=566";
    @Autowired
    private RestTemplate restTemplate;

    public String getQuote() {
        ResponseEntity<QouteResponse> response = restTemplate.exchange(API, HttpMethod.GET, null,new Map<String,?>());
//        return Objects.requireNonNull(response.getBody()).toString();
         System.out.println(response.getBody());
        return "Working";
    }
}
