package com.jobSearchEngine.controllers;

import com.jobSearchEngine.database.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public String postClient(@RequestParam(name = "name") String name,
                             @RequestParam(name = "email") String email) {
        return clientService.registerClient(name, email);
    }
}
