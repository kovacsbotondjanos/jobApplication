package com.jobSearchEngine.controllers;

import com.jobSearchEngine.database.DTOs.PositionDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {

    @GetMapping("/{id}")
    public PositionDTO getPositionById(@RequestParam Long id) {
        return new PositionDTO();
    }

    @GetMapping("/search")
    public List<PositionDTO> searchPosition() {
        return List.of(new PositionDTO());
    }

    @PostMapping
    public String postPosition() {
        return "";
    }
}
