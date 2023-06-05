package com.example.vestiback.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("user")
@Data
public class Event {
    @Id
    private String id;
    private Date dateStart;
    private Date dateEnd;
    private String description;
    private List<Item> outfit;
}
