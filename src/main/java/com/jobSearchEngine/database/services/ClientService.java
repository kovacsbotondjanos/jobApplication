package com.jobSearchEngine.database.services;

import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.repositories.ClientRepository;
import com.jobSearchEngine.exceptions.ClientAlreadyExistsException;
import com.jobSearchEngine.exceptions.InvalidFormDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public String registerClient(String name, String email) {
        if(name == null || email == null) {
            throw new InvalidFormDataException("Please provide an email address and a name too");
        }

        Optional<Client> c = clientRepository.findByNameOrEmail(name, email);
        if(c.isPresent()) {
            throw new ClientAlreadyExistsException();
        }

        if(name.isEmpty() || name.length() > 100) {
            throw new InvalidFormDataException("The length of the name has to be between 1 and 100 characters");
        }

        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        if(email.isEmpty() || !m.matches()) {
            throw new InvalidFormDataException("The email address has an invalid format");
        }

        String apiKey = UUID.randomUUID().toString();

        clientRepository.save(new Client(name, email, apiKey));

        return apiKey;
    }

    public Optional<Client> findByAPIKey(String APIKey) {
        return clientRepository.findByAPIKey(APIKey);
    }
}
