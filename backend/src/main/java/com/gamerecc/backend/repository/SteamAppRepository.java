package com.gamerecc.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamerecc.backend.model.SteamApp;

/**
 * Repository interface for managing SteamApp entities in the database. Extends JpaRepository to provide CRUD operations and additional query methods.
 * SteamAppRepository
 */
public interface SteamAppRepository extends JpaRepository<SteamApp, Integer>
{

}
