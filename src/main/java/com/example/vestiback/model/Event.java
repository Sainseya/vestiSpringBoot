package com.example.vestiback.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Data
public class Event {
    @Id
    private String eventId;
    private Date dateStart;
    private Date dateEnd;
    private String description;
    private List<Item> outfit;

    public void setOutfit(List<Item> outfit) {
        this.outfit = outfit;
    }

    public List<Item> getOutfit() {
        return this.outfit;
    }
}
