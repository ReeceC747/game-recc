package com.gamerecc.backend.model;

public class SteamApp 
{
    private int appid;
    private String name;
    private long last_modified;
    private long price_change_number;

    public SteamApp()
    {

    }

    // ----------------------------------------------------------------------
    // Getters and Setters
    public int getAppid()
    {
        return appid;
    }

    public void setAppid(int appid)
    {
        this.appid = appid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getLast_modified()
    {
        return last_modified;
    }

    public void setLast_modified(long last_modified)
    {
        this.last_modified = last_modified;
    }

    public long getPrice_change_number()
    {
        return price_change_number;
    }

    public void setPrice_change_number(long price_change_number)
    {
        this.price_change_number = price_change_number;
    }
    
}
