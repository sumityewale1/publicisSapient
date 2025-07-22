package com.publicissapient.football.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class League {
    @JsonProperty("league_id")
    private String leagueId;

    @JsonProperty("league_name")
    private String leagueName;

    @JsonProperty("country_id")
    private String countryId;

    @JsonProperty("country_name")
    private String countryName; // OPTIONAL but recommended if present
}