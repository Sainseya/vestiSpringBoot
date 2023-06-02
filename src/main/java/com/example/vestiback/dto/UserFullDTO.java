package com.example.vestiback.dto;

import lombok.Data;

import java.util.List;
@Data
public class UserFullDTO {
    private String id;
    private String name;
    private String surname;
    private String pseudo;
    private String mail;
    private String gender;
    private String accountType;
    private List<WardrobeDTO> wardrobes;
    private List<EventDTO> events;
}
