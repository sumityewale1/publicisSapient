package com.publicissapient.football.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${offline.mode}")
    private boolean offlineMode;

    public boolean isOfflineMode() {
        return offlineMode;
    }
}
