package com.example.vestiback.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class OutfitDTO {
    private String id;
    private Date dateStart;
    private Date dateEnd;
    private String description;
    private List<wardrobeDTO> outfit;
}
