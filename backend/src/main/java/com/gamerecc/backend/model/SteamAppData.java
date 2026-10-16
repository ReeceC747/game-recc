package com.gamerecc.backend.model;

import java.util.List;

/**
 * Represents the data structure for the list of Steam applications returned by the Steam API.
 */
public class SteamAppData 
{
    private List<SteamApp> apps;

    public SteamAppData()
    {

    }

    public List<SteamApp> getApps() 
    {
        return apps;
    }

    public void setApps(List<SteamApp> apps) 
    {
        this.apps = apps;
    }



}



