package com.example.vestiback.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;
@Data
public class UserShortDTO {
    private String name;
    private String surname;
    private String pseudo;
    private String mail;
    private String gender;
    private String accountType;
    private String role;

}
