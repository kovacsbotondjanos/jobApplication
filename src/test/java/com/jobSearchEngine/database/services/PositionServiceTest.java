package com.jobSearchEngine.database.services;

import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.entities.Location;
import com.jobSearchEngine.database.entities.Position;
import com.jobSearchEngine.database.repositories.ClientRepository;
import com.jobSearchEngine.database.repositories.LocationRepository;
import com.jobSearchEngine.database.repositories.PositionRepository;
import com.jobSearchEngine.exceptions.InvalidFormDataException;
import com.jobSearchEngine.exceptions.NotFoundException;
import com.jobSearchEngine.exceptions.UnauthenticatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class PositionServiceTest {

    @Mock
    private PositionRepository positionRepository;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private LocationRepository locationRepository;
    private PositionService positionService;
    private String apiKey = "APIKEY";

    @BeforeEach
    void onSetUp() {
        MockitoAnnotations.initMocks(this);
        positionService = new PositionService(positionRepository,
                                              clientRepository,
                                              locationRepository);
    }

    @Test
    void createNewPosition() {
        given(clientRepository.findByAPIKey(apiKey))
                .willReturn(Optional.empty());
        assertThrows(UnauthenticatedException.class,
                () -> positionService.createNewPosition(apiKey, null, null));

        Client client = new Client("Andy", "andy@andy.com", apiKey);
        Location loc = new Location("Location");
        Position pos = new Position("Job", loc, client);
        given(clientRepository.findByAPIKey(apiKey))
                .willReturn(Optional.of(client));
        given(locationRepository.findByName("Location"))
                .willReturn(Optional.of(loc));
        given(positionRepository.save(pos))
                .willReturn(pos);
        assertThrows(InvalidFormDataException.class,
                () -> positionService.createNewPosition(apiKey, "", ""));
        assertEquals("Job",
                positionService.createNewPosition(apiKey, "Job", "Location").getName());
    }

    @Test
    void findPositionByNameLike() {
        given(clientRepository.findByAPIKey(apiKey))
                .willReturn(Optional.empty());
        assertThrows(UnauthenticatedException.class,
                () -> positionService.findPositionByNameLike(apiKey, null));

        List<Position> positions = List.of(
                new Position("Ada"),
                new Position("Java"),
                new Position("Haskell")
        );
        positions.forEach(p -> p.setId(1L));
        Client client = new Client("Andy", "andy@andy.com", apiKey);
        given(clientRepository.findByAPIKey(apiKey))
                .willReturn(Optional.of(client));
        given(positionRepository.findByClientAndNameLike(1L, "a"))
                .willReturn(positions);
        assertTrue(positionService.findPositionByNameLike(apiKey, "a")
                .stream().allMatch(str -> str.equals("localhost:8080/position/1")));
    }

    @Test
    void findPositionById() {
        given(clientRepository.findByAPIKey(apiKey))
                .willReturn(Optional.empty());
        assertThrows(UnauthenticatedException.class,
                () -> positionService.findPositionById(apiKey, null));

        Client client = new Client("Andy", "andy@andy.com", apiKey);
        Location loc = new Location("Location");
        Position pos = new Position("Job", loc, client);
        given(clientRepository.findByAPIKey(apiKey))
                .willReturn(Optional.of(client));
        given(positionRepository.findByClientAndId(client, 1L))
                .willReturn(Optional.empty());
        assertThrows(NotFoundException.class,
                () -> positionService.findPositionById(apiKey, null));

        given(positionRepository.findByClientAndId(client, 1L))
                .willReturn(Optional.of(new Position("Job", loc, client)));
        assertEquals("Job", positionService.findPositionById(apiKey, 1L).getName());
    }
}