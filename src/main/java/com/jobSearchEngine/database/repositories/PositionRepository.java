package com.jobSearchEngine.database.repositories;

import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {
    @Query(value = "SELECT p.* " +
                    "FROM position p " +
                    "INNER JOIN client c ON p.client_id = c.id " +
                    "WHERE p.name LIKE '%' || :name || '%' AND c.id = :clientId " +
                    "    ORDER BY CASE " +
                    "       WHEN p.name LIKE :name || '%' THEN 1 " +
                    "       ELSE 2 " +
                    "    END",
                    nativeQuery = true)
    List<Position> findByClientAndNameLike(@Param("clientId") Long clientId,@Param("name") String name);
    Optional<Position> findByClientAndId(Client client, Long id);
}
