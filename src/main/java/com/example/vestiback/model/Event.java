package com.example.vestiback.model;

import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class Event {
    private String name;
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
