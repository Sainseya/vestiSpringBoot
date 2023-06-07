package com.example.vestiback.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("user")
@Data
public class Outfit {
    private List<Object> items;
}
