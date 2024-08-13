
package com.example.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class QuoteResponse {

    @JsonProperty("quote")
    private String quote;
    @JsonProperty("author")
    private String author;
}