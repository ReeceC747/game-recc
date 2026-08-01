package com.gamerecc.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
