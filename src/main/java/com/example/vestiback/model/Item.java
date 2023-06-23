package com.example.vestiback.model;

import lombok.Data;
import java.net.URL;

@Data
public class Item {
        private String type;
        private String size;
        private String fit;
        private String season;
        private String color;
        private boolean isFavorite;
        private String label;
        private URL picture;

}
