package com.gamerecc.backend.service;

import java.net.http.HttpClient;

import org.springframework.stereotype.Service;

import com.gamerecc.backend.config.SteamConfig;
import com.gamerecc.backend.model.SteamApiResponse;

import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

/**
 * Service class responsible for interacting with the Steam API. It uses the SteamConfig to retrieve the API key and makes HTTP requests to fetch game data.
 */
@Service
public class SteamService 
{
    private final SteamConfig steamConfig;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    /**
     * Constructor for SteamService. Initializes the HTTP client and object mapper, and retrieves the Steam API key from the provided SteamConfig.
     * @param steamConfig
     */
    public SteamService(SteamConfig steamConfig)
    {
        this.steamConfig = steamConfig;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();

        System.out.println("SteamService created");
    }

    public SteamApiResponse getAppList(int lastAppId, int maxResults) throws IOException, InterruptedException
    {
        String url = 
        "https://api.steampowered.com/IStoreService/GetAppList/v1/"
        + "?key=" + steamConfig.getApiKey()
        + "&max_results=" + maxResults
        + "&last_appid=" + lastAppId;

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        HttpResponse<String> response;

        response = httpClient.send(
        request,
        HttpResponse.BodyHandlers.ofString()
        );

        System.out.println("Status: " + response.statusCode());
        System.out.println(response.body());

        SteamApiResponse steamApiResponse = objectMapper.readValue(response.body(), SteamApiResponse.class);

        return steamApiResponse;
    }
}
