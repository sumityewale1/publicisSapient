package com.publicissapient.football.controller;

import com.publicissapient.football.model.Standing;
import com.publicissapient.football.model.StandingsResponse;
import com.publicissapient.football.service.StandingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class StandingsController {

    private final StandingService standingService;

    @Autowired
    public StandingsController(StandingService standingService) {
        this.standingService = standingService;
    }

    @GetMapping("/standings")
    public ResponseEntity<StandingsResponse> getStandings(
            @RequestParam String country,
            @RequestParam String league,
            @RequestParam String team) {

        Standing standing = standingService.getStanding(country, league, team);
        StandingsResponse response = getStandingsResponse(standing);
        return ResponseEntity.ok(response);
    }

    private static StandingsResponse getStandingsResponse(Standing standing) {
        StandingsResponse response = new StandingsResponse();
        response.setCountryId(standing.getCountryId());
        response.setCountryName(standing.getCountryName());

        response.setLeagueId(standing.getLeagueId());
        response.setLeagueName(standing.getLeagueName());

        response.setTeamId(standing.getTeamId());
        response.setTeamName(standing.getTeamName());

        response.setPosition(standing.getOverallLeaguePosition());
        return response;
    }
}
