package com.jobSearchEngine.database.seeders;

import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.entities.Location;
import com.jobSearchEngine.database.repositories.ClientRepository;
import com.jobSearchEngine.database.seeders.factories.ClientFactory;
import com.jobSearchEngine.database.seeders.factories.LocationFactory;
import com.jobSearchEngine.database.seeders.factories.PositionFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DatabaseSeeder {

    private final ClientFactory clientFactory;
    private final LocationFactory locationFactory;
    private final PositionFactory positionFactory;
    private final ClientRepository clientRepository;

    public DatabaseSeeder(ClientFactory clientFactory, LocationFactory locationFactory, PositionFactory positionFactory, ClientRepository clientRepository) {
        this.clientFactory = clientFactory;
        this.locationFactory = locationFactory;
        this.positionFactory = positionFactory;
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    public void seedDatabaseIfEmpty() {
        if(clientRepository.count() == 0) {
            seedDatabase();
        }
    }

    private void seedDatabase() {
        List<Location> locations = locationFactory.createLocations();
        List<Client> clients = clientFactory.createClients();
        positionFactory.createPositions(clients, locations);
    }
}
