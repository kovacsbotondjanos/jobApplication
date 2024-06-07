package com.jobSearchEngine.database.seeders.factories;

import com.github.javafaker.Faker;
import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.entities.Location;
import com.jobSearchEngine.database.entities.Position;
import com.jobSearchEngine.database.repositories.ClientRepository;
import com.jobSearchEngine.database.repositories.LocationRepository;
import com.jobSearchEngine.database.repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class PositionFactory {

    private final PositionRepository positionRepository;
    private final ClientRepository clientRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public PositionFactory(PositionRepository positionRepository, ClientRepository clientRepository, LocationRepository locationRepository) {
        this.positionRepository = positionRepository;
        this.clientRepository = clientRepository;
        this.locationRepository = locationRepository;
    }

    public void createPositions(List<Client> clients, List<Location> locations) {
        IntStream.range(0, 20)
                .forEach(__ -> {
                    Random r = new Random();
                    Faker faker = new Faker(new Random());
                    String name = faker.company().profession();
                    Position p = new Position(name);

                    Location loc = locations.get(r.nextInt(Integer.MAX_VALUE) % (locations.size()));
                    Client c = clients.get(r.nextInt(Integer.MAX_VALUE) % (clients.size()));

                    p.setLocation(loc);
                    p.setClient(c);

                    loc.addPosition(p);
                    c.addPosition(p);

                    positionRepository.save(p);
                    locationRepository.save(loc);
                    clientRepository.save(c);
                });
    }
}
