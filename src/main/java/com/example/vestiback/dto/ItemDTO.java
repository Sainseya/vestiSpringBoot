package com.example.vestiback.dto;
import lombok.Data;
@Data
public class ItemDTO {
    private String id;
    private String name;
    private String label;
    private String type;
    private String season;
    private String color;
    private String size;
    private String fit;
    private boolean favorite;
}
