package com.jobSearchEngine.database.repositories;

import com.jobSearchEngine.database.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
