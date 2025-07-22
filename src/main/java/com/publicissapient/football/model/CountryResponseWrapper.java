package com.publicissapient.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CountryResponseWrapper {
    @JsonProperty("results") // Adjust based on actual key in response
    private List<Country> results;

    public List<Country> getResults() {
        return results;
    }

    public void setResults(List<Country> results) {
        this.results = results;
    }
}
