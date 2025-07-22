package com.publicissapient.football.service;

import com.publicissapient.football.model.Country;
import com.publicissapient.football.model.League;
import com.publicissapient.football.model.Standing;

import java.util.List;

/**
 * Service interface for fetching football-related data such as countries, leagues, and standings.
 * Implementations of this interface can provide data from online sources or offline mock data.
 */
public interface FootballApiService {

    /**
     * Retrieves the list of all countries participating in football leagues.
     *
     * @return a list of {@link Country} objects. Never {@code null}, but possibly empty if no data is available.
     */
    List<Country> getCountries();

    /**
     * Retrieves the list of football leagues for the given country.
     *
     * @param countryId the unique identifier of the country whose leagues are to be fetched.
     *                  If {@code null}, all leagues may be returned (implementation dependent).
     * @return a list of {@link League} objects for the specified country. Never {@code null}, but possibly empty.
     */
    List<League> getLeagues(String countryId);

    /**
     * Retrieves the list of standings for the given league.
     *
     * @param leagueId the unique identifier of the league whose standings are to be fetched.
     *                 If {@code null}, all standings may be returned (implementation dependent).
     * @return a list of {@link Standing} objects for the specified league. Never {@code null}, but possibly empty.
     */
    List<Standing> getStandings(String leagueId);
}
