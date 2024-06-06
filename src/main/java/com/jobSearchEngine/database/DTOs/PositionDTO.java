package com.jobSearchEngine.database.DTOs;

import com.jobSearchEngine.database.entities.Position;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
public class PositionDTO {
    private Long id;
    private String name;
    private String location;
    private String client;
    private Date createdAt;
    private Date updatedAt;

    public PositionDTO(Position position) {
        this.id = position.getId();
        this.name = position.getName();
        this.location = position.getLocation().getName();
        this.client = position.getClient().getName();
        this.createdAt = position.getCreatedAt();
        this.updatedAt = position.getUpdatedAt();
    }

    public static PositionDTO of(Position position) {
        return new PositionDTO(position);
    }
}
