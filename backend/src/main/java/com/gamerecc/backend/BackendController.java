package com.gamerecc.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackendController
{

    public BackendController()
    {
        System.out.println(">>> BackendController constructed <<<");
    }

    @GetMapping("/")
    public String getBackendStatus()
    {
        return "GameRecc Backend Running";
    }
}