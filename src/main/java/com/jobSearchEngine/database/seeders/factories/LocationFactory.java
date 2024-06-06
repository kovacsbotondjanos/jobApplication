package com.jobSearchEngine.database.seeders.factories;

import com.github.javafaker.Faker;
import com.jobSearchEngine.database.entities.Location;
import com.jobSearchEngine.database.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

@Service
public class LocationFactory {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationFactory(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> createLocations() {
        List<Location> list = new CopyOnWriteArrayList<>();
        IntStream.range(0, 5)
                .forEach(__ -> {
                    Faker faker = new Faker(new Random());
                    String name = faker.address().cityName();
                    Location loc = new Location(name);
                    list.add(locationRepository.save(loc));
                });
        return list;
    }
}
