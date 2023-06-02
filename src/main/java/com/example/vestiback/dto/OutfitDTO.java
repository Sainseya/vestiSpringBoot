package com.example.vestiback.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class OutfitDTO {

    @Id
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
