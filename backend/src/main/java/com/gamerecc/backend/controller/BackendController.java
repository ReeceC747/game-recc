package com.gamerecc.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController
{

    @GetMapping("/")
    public String getBackendStatus()
    {
        return "GameRecc Backend Running";
    }
}