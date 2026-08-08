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

        getAppList();
    }

    public String getAppList()
    {
        String url = 
        "https://api.steampowered.com/IStoreService/GetAppList/v1/"
        + "?key=" + steamConfig.getApiKey()
        + "&max_results=10";

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        HttpResponse<String> response;

        try
        {
            response = httpClient.send(
            request,
            HttpResponse.BodyHandlers.ofString()
            );

            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());

            SteamApiResponse steamApiResponse = objectMapper.readValue(response.body(), SteamApiResponse.class);

            System.out.println(steamApiResponse.getResponse().getApps().getFirst().getName());

            return response.body();
        }
        catch (IOException e)
        {
            System.out.println("Failed to communicate with Steam.");
            e.printStackTrace();

            return "Steam request failed: ";
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();

            System.out.println("Steam request interrupted.");
            e.printStackTrace();

            return "Steam request interrupted: ";
        }
    }
}
