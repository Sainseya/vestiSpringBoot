package com.example.vestiback.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;

@Document("user")
@Data
public class Item {
        @Id
        private String id;
        private String name;
        private String label;
        private String season;
        private String type;
        private String color;
        private String size;
        private boolean favorite;
        private URL linkImage;
        private String fit;

}
