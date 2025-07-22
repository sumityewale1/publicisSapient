package com.publicissapient.football.serviceimpl;

import com.publicissapient.football.exception.ResourceNotFoundException;
import com.publicissapient.football.model.Country;
import com.publicissapient.football.model.League;
import com.publicissapient.football.model.Standing;
import com.publicissapient.football.service.FootballApiService;
import com.publicissapient.football.service.StandingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandingServiceImpl implements StandingService {

    private final FootballApiService apiService;

    public StandingServiceImpl(
            FootballApiServiceImpl liveService,
            OfflineFootballServiceImpl offlineService,
            @Value("${offline.mode:false}") boolean offline
    ) {
        this.apiService = offline ? offlineService : liveService;
    }

    @Override
    public Standing getStanding(String countryName, String leagueName, String teamName) {
        // Get Country ID
        List<Country> countries = apiService.getCountries();
        String countryId = countries.stream()
                .filter(c -> countryName.equalsIgnoreCase(c.getCountryName()))
                .findFirst()
                .orElseThrow(() -> {
                    List<String> list = new ArrayList<>();
                    for (Country country : countries) {
                        String name = country.getCountryName();
                        list.add(name);
                    }
                    return new ResourceNotFoundException(
                            "Country not found: " + countryName + ". Available: " + list
                    );
                })
                .getCountryId();

        // Get League ID
        List<League> leagues = apiService.getLeagues(countryId);
        String leagueId = leagues.stream()
                .filter(l -> leagueName.equalsIgnoreCase(l.getLeagueName()))
                .findFirst()
                .orElseThrow(() -> {
                    List<String> list = new ArrayList<>();
                    for (League league : leagues) {
                        String name = league.getLeagueName();
                        list.add(name);
                    }
                    return new ResourceNotFoundException(
                            "League not found: " + leagueName + " under country: " + countryName +
                            ". Available: " + list
                    );
                })
                .getLeagueId();

        // Get Team Standing
        List<Standing> standings = apiService.getStandings(leagueId);
        return standings.stream()
                .filter(s -> teamName.equalsIgnoreCase(s.getTeamName()))
                .findFirst()
                .orElseThrow(() -> {
                    List<String> list = new ArrayList<>();
                    for (Standing standing : standings) {
                        String name = standing.getTeamName();
                        list.add(name);
                    }
                    return new ResourceNotFoundException(
                            "Team not found: " + teamName + " under league: " + leagueName +
                            ". Available: " + list
                    );
                });
    }
}
