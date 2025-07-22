package com.publicissapient.football.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandingsResponse {

    private String countryId;
    private String countryName;

    private String leagueId;
    private String leagueName;

    private String teamId;
    private String teamName;

    private String position;
}
