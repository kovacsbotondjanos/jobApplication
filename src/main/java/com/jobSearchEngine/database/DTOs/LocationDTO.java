package com.jobSearchEngine.database.DTOs;

import com.jobSearchEngine.database.entities.Client;
import com.jobSearchEngine.database.entities.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
public class LocationDTO {
    private Long id;
    private String name;
    private List<PositionDTO> positions;
    private Date createdAt;
    private Date updatedAt;

    public LocationDTO(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.positions = location.getPositions().stream()
                .map(PositionDTO::of).collect(Collectors.toList());
        this.createdAt = location.getCreatedAt();
        this.updatedAt = location.getUpdatedAt();
    }

    public static LocationDTO of(Location location) {
        return new LocationDTO(location);
    }
}
