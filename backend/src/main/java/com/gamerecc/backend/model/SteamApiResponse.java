package com.gamerecc.backend.model;

/**
 * Represents the response from the Steam API when fetching game data.
 */
public class SteamApiResponse 
{
    private SteamAppData response;

    public SteamApiResponse()
    {

    }

    public SteamAppData getResponse() 
    {
        return response;
    }

    public void setResponse(SteamAppData response) 
    {
        this.response = response;
    }
}
