package com.jobSearchEngine.controllers;

import com.jobSearchEngine.database.DTOs.PositionDTO;
import com.jobSearchEngine.database.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/{id}")
    public PositionDTO getPositionById(@RequestHeader(value = "API_KEY") String apiKey,
                                       @PathVariable Long id) {
        return positionService.findPositionById(apiKey, id);
    }

    @GetMapping("/search")
    public List<String> searchPosition(@RequestParam(value = "API_KEY") String apiKey,
                                       @RequestParam(value = "name") String name) {
        return positionService.findPositionByNameLike(apiKey, name);
    }

    @PostMapping
    public String postPosition(@RequestParam(value = "API_KEY") String apiKey,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "location") String location) {
        return "localhost:8080/position/" + positionService.createNewPosition(apiKey, name, location).getId();
    }
}
