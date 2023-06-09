package com.example.vestiback.model;

import lombok.Data;
import java.util.List;
@Data
public class Wardrobe {
        private String name;
        private List<Item> items;
}
