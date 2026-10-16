package com.gamerecc.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Grabs Steam API key from application.properties and provides it to the SteamService class.
 */
@Component
public class SteamConfig 
{
    private final String apiKey;

    public SteamConfig(@Value("${steam.api.key}") String apiKey)
    {
        this.apiKey = apiKey;

        if (apiKey == null || apiKey.isEmpty())
        {
            System.out.println("Steam API Key is missing.");
        }
        else
        {
            System.out.println("Steam API Key loaded successfully.");
        }
    }

    public String getApiKey()
    {
        return apiKey;
    }
}
