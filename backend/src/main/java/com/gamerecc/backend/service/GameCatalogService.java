package com.gamerecc.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import com.gamerecc.backend.repository.SteamAppRepository;
import com.gamerecc.backend.model.SteamApiResponse;
import com.gamerecc.backend.model.SteamApp;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Service class responsible for managing the game catalog. It interacts with the SteamService to fetch game data and the SteamAppRepository to store and retrieve game information from the database.
 * GameCatalogService
 */
@Service
public class GameCatalogService
{
    private final SteamService steamService;
    private final SteamAppRepository steamAppRepository;

    /**
     * Constructor for GameCatalogService. Initializes the SteamService and SteamAppRepository for managing the game catalog.
     * @param steamService
     * @param steamAppRepository
     */
    public GameCatalogService(SteamService steamService, SteamAppRepository steamAppRepository)
    {
        this.steamService = steamService;
        this.steamAppRepository = steamAppRepository;
    }

    /**
     * Fetches the list of games from the Steam API based off the last appid in the database and saves them to the database.
     * @throws IOException
     * @throws InterruptedException
     */
    public void updateGameCatalog() throws IOException, InterruptedException
    {
        SteamApiResponse steamApiResponse = steamService.getAppList(0, 50000);

        List<SteamApp> apps = steamApiResponse.getResponse().getApps();

        steamAppRepository.saveAll(apps);
    }


    /**
     * Refreshes the game catalog by fetching data from the Steam API and saving it to the database. It continues fetching until there are no more apps to retrieve.
     * @return The total number of games added to the catalog.
     * @throws IOException
     * @throws InterruptedException
     */
    public int refreshCatalog() throws IOException, InterruptedException
    {
        int lastAppId = 0;
        int totalAppsSaved = 0;

        while (true)
        {
            SteamApiResponse response = steamService.getAppList(lastAppId, 50000);

            List<SteamApp> apps =
                response.getResponse().getApps();

                if(apps == null || apps.isEmpty())
                {
                    break;
                }

                steamAppRepository.saveAll(apps);
                totalAppsSaved += apps.size();

                lastAppId = apps.getLast().getAppid();
        }
        return totalAppsSaved;
    }

    /**
     * Retrieves a random game from the catalog.
     * @return 
     */
    public SteamApp getRandomGame()
    {
        long gameCount = steamAppRepository.count();

        if(gameCount == 0)
        {
            throw new IllegalStateException("Game catalog is empty.");
        }

        int randomIndex = ThreadLocalRandom.current().nextInt((int) gameCount);

        return steamAppRepository.findAll(PageRequest.of(randomIndex, 1))
        .getContent()
        .getFirst();
    }

    public long getCatalogCount()
    {
        return steamAppRepository.count();
    }
}
