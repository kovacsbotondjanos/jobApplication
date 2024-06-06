package com.jobSearchEngine.database.services;

import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.repositories.ClientRepository;
import com.jobSearchEngine.exceptions.ClientAlreadyExistsException;
import com.jobSearchEngine.exceptions.InvalidFormDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    private ClientService clientService;

    @BeforeEach
    void onSetUp() {
        MockitoAnnotations.initMocks(this);
        clientService = new ClientService(clientRepository);
    }

    @Test
    void registerClientWithMissingArgsTest() {
        assertThrows(InvalidFormDataException.class,
                () -> clientService.registerClient(null, null));
        assertThrows(InvalidFormDataException.class,
                () -> clientService.registerClient("Client", null));
        assertThrows(InvalidFormDataException.class,
                () -> clientService.registerClient(null, "email"));
    }

    @Test
    void registerClientDuplicateTest() {
        String name = "Client";
        String email = "email";
        given(clientRepository.findByNameOrEmail(name, email))
                .willReturn(Optional.of(new Client()));
        assertThrows(ClientAlreadyExistsException.class,
                () -> clientService.registerClient(name, email));
    }

    @Test
    void registerClientWithWrongFormattedArgsTest() {
        String name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                      "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                      "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                      "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                      "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                      "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String email = "email";
        String nameCorrect = "Client";
        String emailCorrect = "User@User.com";
        given(clientRepository.findByNameOrEmail(name, email))
                .willReturn(Optional.empty());

        assertThrows(InvalidFormDataException.class,
                () -> clientService.registerClient(name, emailCorrect));
        assertThrows(InvalidFormDataException.class,
                () -> clientService.registerClient(nameCorrect, email));
    }

    @Test
    void registerClientTest() {
        String nameCorrect = "Client";
        String emailCorrect = "User@User.com";
        given(clientRepository.findByNameOrEmail(nameCorrect, emailCorrect))
                .willReturn(Optional.empty());
        assertDoesNotThrow(() -> clientService.registerClient(nameCorrect, emailCorrect));
    }
}