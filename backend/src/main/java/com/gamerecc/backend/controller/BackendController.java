package com.gamerecc.backend.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.gamerecc.backend.model.SteamApp;
import com.gamerecc.backend.service.GameCatalogService;

/**
 * The main controller for the GameRecc backend. Handles HTTP requests and responses
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BackendController
{
    private final GameCatalogService gameCatalogService;

    /**
     * Constructor for BackendController.
     * @param gameCatalogService The service to manage the game catalog.
     */
    public BackendController(GameCatalogService gameCatalogService)
    {
        this.gameCatalogService = gameCatalogService;
    }

    /**
     * Root endpoint to check if the backend is running.
     * @return A string indicating the backend is running.
     */
    @GetMapping("/")
    public String getBackendStatus()
    {
        return "GameRecc Backend Running";
    }

    /**
     * Endpoint to get a random game from the catalog.
     * @return A SteamApp object representing a random game.
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping("/random")
    public SteamApp getRandomGame() throws IOException, InterruptedException
    {
        return gameCatalogService.getRandomGame();
    }


    /**
     * Endpoint to refresh the game catalog by fetching data from the Steam API.
     * @return The number of games added to the catalog.
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping("/catalog/refresh")
    public int refreshCatalog() throws IOException, InterruptedException
    {
        return gameCatalogService.refreshCatalog();
    }

    /**
     * Endpoint to get the count of games in the catalog.
     * @return The total number of games in the catalog.
     */
    @GetMapping ("/catalog/count")
    public long getCatalogCount()
    {
        return gameCatalogService.getCatalogCount();
    }

}