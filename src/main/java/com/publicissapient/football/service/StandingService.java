package com.publicissapient.football.service;

import com.publicissapient.football.model.Standing;

/**
 * Service for retrieving the football standing of a team within a league and country.
 */
public interface StandingService {
    /**
     * Retrieves the standing for a given country, league, and team.
     *
     * @param countryName the name of the country
     * @param leagueName  the name of the league
     * @param teamName    the name of the team
     * @return the {@link Standing} of the team, or null if not found
     */
    Standing getStanding(String countryName, String leagueName, String teamName);
}
