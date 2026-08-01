package com.gamerecc.backend.service;

import org.springframework.stereotype.Service;

import com.gamerecc.backend.config.SteamConfig;

@Service
public class SteamService 
{
    private final SteamConfig steamconfig;

    public SteamService(SteamConfig steamconfig)
    {
        this.steamconfig = steamconfig;

        System.out.println("SteamService created");
    }
}
