package com.example.vestiback.dto;
import lombok.Data;
import java.util.List;

@Data
public class wardrobeDTO {
    private String id;
    private String name;
    private List<ItemDTO> items;
}
