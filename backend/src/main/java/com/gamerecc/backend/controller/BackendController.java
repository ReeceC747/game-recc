package com.gamerecc.backend.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gamerecc.backend.model.SteamApp;
import com.gamerecc.backend.service.SteamService;
import com.gamerecc.backend.service.GameCatalogService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BackendController
{
    private final SteamService steamService;
    private final GameCatalogService gameCatalogService;

    public BackendController(SteamService steamService, GameCatalogService gameCatalogService)
    {
        this.steamService = steamService;
        this.gameCatalogService = gameCatalogService;
    }

    @GetMapping("/")
    public String getBackendStatus()
    {
        return "GameRecc Backend Running";
    }

    @GetMapping("/random")
    public SteamApp getRandomGame() throws IOException, InterruptedException
    {
        return steamService.getRandomGame();
    }

    @GetMapping("/catalog/refresh")
    public int refreshCatalog() throws IOException, InterruptedException
    {
        return gameCatalogService.refreshCatalog();
    }

    @GetMapping ("/catalog/count")
    public long getCatalogCount()
    {
        return gameCatalogService.getCatalogCount();
    }

}