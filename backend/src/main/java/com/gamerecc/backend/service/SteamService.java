package com.gamerecc.backend.service;

import java.net.http.HttpClient;

import org.springframework.stereotype.Service;

import com.gamerecc.backend.config.SteamConfig;
import com.gamerecc.backend.model.SteamApiResponse;
import com.gamerecc.backend.model.SteamApp;

import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import java.io.IOException;

@Service
public class SteamService 
{
    private final SteamConfig steamConfig;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

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
