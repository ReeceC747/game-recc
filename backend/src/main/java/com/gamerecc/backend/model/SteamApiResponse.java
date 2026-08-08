package com.gamerecc.backend.model;

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
