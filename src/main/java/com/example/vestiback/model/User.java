package com.example.vestiback.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document("user")
@Data
public class User {
    @Id
    private String userId;
    private String username;
    private String email;
    private String gender;
    private String accountType;
    private List<Wardrobe> wardrobes;
    private List<Event> events;
    private List<Event> outfitHistory;
}
