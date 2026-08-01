package com.gamerecc.backend.service;

import java.net.http.HttpClient;

import org.springframework.stereotype.Service;

import com.gamerecc.backend.config.SteamConfig;

@Service
public class SteamService 
{
    private final SteamConfig steamConfig;
    private final HttpClient httpClient;

    public SteamService(SteamConfig steamConfig)
    {
        this.steamConfig = steamConfig;
        this.httpClient = HttpClient.newHttpClient();

        System.out.println("SteamService created");

        getAppList();
    }

    public String getAppList()
    {
        String url = 
        "https://partner.steam-api.com/IStoreService/GetAppList/v1/"
        + "?key=" + steamConfig.getApiKey()
        + "&max_results=10";

        return "not implemented yet"; 

    }
}
