package com.gamerecc.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamerecc.backend.model.SteamApp;

public interface SteamAppRepository extends JpaRepository<SteamApp, Integer>
{
    
}
