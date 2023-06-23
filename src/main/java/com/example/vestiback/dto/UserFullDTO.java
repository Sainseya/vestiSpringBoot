package com.example.vestiback.dto;

import lombok.Data;

import java.util.List;
@Data
public class UserFullDTO {
    private String id;
    private String username;
    private String email;
    private String accountType;
    private List<WardrobeDTO> wardrobes;
    private List<EventDTO> events;
}
