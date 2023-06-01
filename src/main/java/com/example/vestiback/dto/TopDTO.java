package com.example.vestiback.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TopDTO {
    @Id
    private String id;
    private String name;
    private String label;
    private String season;
    private String type;
    private String color;
    private String size;
    private boolean favorite;
    private String fit;
}
