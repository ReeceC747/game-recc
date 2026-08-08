package com.gamerecc.backend.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamerecc.backend.model.SteamApp;
import com.gamerecc.backend.service.SteamService;

@RestController
public class BackendController
{
    private final SteamService steamService;

    public BackendController(SteamService steamService)
    {
        this.steamService = steamService;
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
    
}