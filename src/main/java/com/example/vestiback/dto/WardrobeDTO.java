package com.example.vestiback.dto;
import com.example.vestiback.model.Item;
import lombok.Data;
import java.util.List;

@Data
public class WardrobeDTO {
    private String name;
    private List<Item> items;
}
