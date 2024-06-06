package com.jobSearchEngine.database.services;

import com.jobSearchEngine.database.DTOs.PositionDTO;
import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.entities.Location;
import com.jobSearchEngine.database.entities.Position;
import com.jobSearchEngine.database.repositories.ClientRepository;
import com.jobSearchEngine.database.repositories.LocationRepository;
import com.jobSearchEngine.database.repositories.PositionRepository;
import com.jobSearchEngine.exceptions.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository, ClientRepository clientRepository, LocationRepository locationRepository) {
        this.positionRepository = positionRepository;
        this.clientRepository = clientRepository;
        this.locationRepository = locationRepository;
    }

    public PositionDTO createNewPosition(String APIKey, String name, String locationName) {
        Client client = clientRepository.findByAPIKey(APIKey)
                .orElseThrow(UnauthenticatedException::new);

        Optional<Location> loc = locationRepository.findByName(locationName);
        Location location = loc.orElseGet(() -> new Location(locationName));

        Position p = new Position(name, location, client);

        p.setLocation(location);
        location.addPosition(p);

        locationRepository.save(location);
        return PositionDTO.of(positionRepository.save(p));
    }

    public List<String> findPositionByNameLike(String APIKey, String name) {
        Client client = clientRepository.findByAPIKey(APIKey)
                .orElseThrow(UnauthenticatedException::new);
        return positionRepository.findByClientAndNameLike(client.getId(), name)
                .stream().map(p -> "localhost:8080/position/" + p.getId()).collect(Collectors.toList());
    }

    public PositionDTO findPositionById(String APIKey, Long id) {
        Client client = clientRepository.findByAPIKey(APIKey)
                .orElseThrow(UnauthenticatedException::new);

        return PositionDTO.of(positionRepository.findByClientAndId(client, id)
                                    .orElseThrow(UnauthenticatedException::new));
    }
}
