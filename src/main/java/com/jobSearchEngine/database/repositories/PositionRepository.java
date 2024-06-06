package com.jobSearchEngine.database.repositories;

import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
