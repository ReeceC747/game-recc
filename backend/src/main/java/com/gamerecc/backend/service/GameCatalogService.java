package com.gamerecc.backend.service;

import org.springframework.stereotype.Service;

import com.gamerecc.backend.repository.SteamAppRepository;
import com.gamerecc.backend.model.SteamApiResponse;
import com.gamerecc.backend.model.SteamApp;

import java.io.IOException;
import java.util.List;

@Service
public class GameCatalogService
{
    private final SteamService steamService;
    private final SteamAppRepository steamAppRepository;

    public GameCatalogService(SteamService steamService, SteamAppRepository steamAppRepository)
    {
        this.steamService = steamService;
        this.steamAppRepository = steamAppRepository;
    }

    public void updateGameCatalog() throws IOException, InterruptedException
    {
        SteamApiResponse steamApiResponse = steamService.getAppList();

        List<SteamApp> apps = steamApiResponse.getResponse().getApps();

        steamAppRepository.saveAll(apps);
    }


    public int refreshCatalog() throws IOException, InterruptedException
    {
        SteamApiResponse steamApiResponse = steamService.getAppList();

        List<SteamApp> apps = steamApiResponse.getResponse().getApps();

        steamAppRepository.saveAll(apps);

        return apps.size();
    }

    public long getCatalogCount()
    {
        return steamAppRepository.count();
    }
}
