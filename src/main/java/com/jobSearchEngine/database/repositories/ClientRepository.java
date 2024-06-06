package com.jobSearchEngine.database.repositories;

import com.jobSearchEngine.database.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByNameOrEmail(String name, String email);
}
