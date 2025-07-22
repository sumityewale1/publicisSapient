package com.publicissapient.football.serviceimpl;

import com.publicissapient.football.model.Country;
import com.publicissapient.football.model.League;
import com.publicissapient.football.model.Standing;
import com.publicissapient.football.service.FootballApiService;
import com.publicissapient.football.util.AESUtil;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FootballApiServiceImpl implements FootballApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FootballApiServiceImpl.class);

    @Value("${api.base-url}")
    private String baseUrl;

    private final String decryptedApiKey;
    @Getter
    private final RestTemplate restTemplate;

    public FootballApiServiceImpl(
            @Value("${api.key.encrypted}") String encryptedKey,
            @Value("${aes.secret}") String secretKey
    ) {
        this.decryptedApiKey = AESUtil.decrypt(encryptedKey, secretKey);
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Country> getCountries() {
        LOGGER.debug("Calling API to fetch all countries");

        String url = baseUrl + "?action=get_countries&APIkey=" + decryptedApiKey;
        Country[] countries = restTemplate.getForObject(url, Country[].class);
        return countries != null ? Arrays.asList(countries) : Collections.emptyList();
    }

    @Override
    public List<League> getLeagues(String countryId) {
        String url = baseUrl + "?action=get_leagues&country_id=" + countryId + "&APIkey=" + decryptedApiKey;
        League[] leagues = restTemplate.getForObject(url, League[].class);
        return leagues != null ? Arrays.stream(leagues)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()) : Collections.emptyList();
    }

    @Override
    public List<Standing> getStandings(String leagueId) {
        String url = baseUrl + "?action=get_standings&league_id=" + leagueId + "&APIkey=" + decryptedApiKey;
        Standing[] standings = restTemplate.getForObject(url, Standing[].class);
        return standings != null ? Arrays.asList(standings) : Collections.emptyList();
    }

}
