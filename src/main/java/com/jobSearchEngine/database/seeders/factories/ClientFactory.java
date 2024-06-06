package com.jobSearchEngine.database.seeders.factories;

import com.github.javafaker.Faker;
import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

@Service
public class ClientFactory {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientFactory(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> createClients() {
        List<Client> list = new CopyOnWriteArrayList<>();
        IntStream.range(0, 10)
                .forEach(__ -> {
                    Faker faker = new Faker(new Random());
                    String name = faker.company().name();
                    String email = faker.internet().safeEmailAddress();
                    Client c = new Client(name, email, UUID.randomUUID().toString());
                    list.add(clientRepository.save(c));
                });
        return list;
    }
}
