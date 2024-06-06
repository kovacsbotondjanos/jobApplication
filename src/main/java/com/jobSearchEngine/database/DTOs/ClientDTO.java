package com.jobSearchEngine.database.DTOs;

import com.jobSearchEngine.database.entities.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@EqualsAndHashCode
public class ClientDTO {
    private Long id;
    private String email;
    private String name;
    private List<PositionDTO> positions;
    private Date createdAt;
    private Date updatedAt;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.email = client.getEmail();
        this.name = client.getName();
        this.positions = client.getPositions().stream().map(PositionDTO::new)
                .collect(Collectors.toList());
        this.createdAt = client.getCreatedAt();
        this.updatedAt = client.getUpdatedAt();
    }

    public static ClientDTO of(Client client) {
        return new ClientDTO(client);
    }
}
