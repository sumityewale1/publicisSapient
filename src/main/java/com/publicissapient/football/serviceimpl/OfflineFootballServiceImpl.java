package com.publicissapient.football.serviceimpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.football.exception.MockDataReadException;
import com.publicissapient.football.model.Country;
import com.publicissapient.football.model.League;
import com.publicissapient.football.model.Standing;
import com.publicissapient.football.service.FootballApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OfflineFootballServiceImpl implements FootballApiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OfflineFootballServiceImpl.class);

    private static final String COUNTRIES_FILE = "mock/countries.json";
    private static final String LEAGUES_FILE = "mock/leagues.json";
    private static final String STANDINGS_FILE = "mock/standings.json";

    private final ObjectMapper mapper;

    @Autowired
    public OfflineFootballServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Country> getCountries() {
        LOGGER.info("Fetching list of countries from offline mock data");
        List<Country> countries = readJson(COUNTRIES_FILE, new TypeReference<List<Country>>() {});
        LOGGER.debug("Fetched {} countries", countries.size());
        return countries;
    }

    @Override
    public List<League> getLeagues(String countryId) {
        LOGGER.info("Fetching leagues for countryId='{}' from offline mock data", countryId);
        List<League> leagues = readJson(LEAGUES_FILE, new TypeReference<List<League>>() {});
        List<League> filteredLeagues =
                (countryId == null)
                        ? leagues
                        : leagues.stream()
                        .filter(league -> Objects.equals(league.getCountryId(), countryId))
                        .collect(Collectors.toList());
        if (filteredLeagues.isEmpty()) {
            LOGGER.warn("No leagues found for countryId='{}'", countryId);
        } else {
            LOGGER.debug("Found {} leagues for countryId='{}'", filteredLeagues.size(), countryId);
        }
        return filteredLeagues;
    }

    @Override
    public List<Standing> getStandings(String leagueId) {
        LOGGER.info("Fetching standings for leagueId='{}' from offline mock data", leagueId);
        List<Standing> standings = readJson(STANDINGS_FILE, new TypeReference<List<Standing>>() {});
        List<Standing> filteredStandings =
                (leagueId == null)
                        ? standings
                        : standings.stream()
                        .filter(standing -> Objects.equals(standing.getLeagueId(), leagueId))
                        .collect(Collectors.toList());
        if (filteredStandings.isEmpty()) {
            LOGGER.warn("No standings found for leagueId='{}'", leagueId);
        } else {
            LOGGER.debug("Found {} standings for leagueId='{}'", filteredStandings.size(), leagueId);
        }
        return filteredStandings;
    }

    private <T> List<T> readJson(String path, TypeReference<List<T>> type) {
        try (InputStream is = new ClassPathResource(path).getInputStream()) {
            LOGGER.debug("Attempting to read mock JSON from path: {}", path);
            List<T> data = mapper.readValue(is, type);
            LOGGER.debug("Successfully read {} records from {}", data.size(), path);
            return data;
        } catch (Exception e) {
            LOGGER.error("Failed to read or parse mock data from [{}]: {}", path, e.getMessage(), e);
            throw new MockDataReadException("Failed to read mock data: " + path, e);
        }
    }
}
