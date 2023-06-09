package com.example.vestiback.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;


@Data
public class Item {
        @Id
        private String itemId;
        private String type;
        private String size;
        private String fit;
        private String season;
        private String color;
        private boolean isFavorite;
        private String label;
        private URL picture;

}
