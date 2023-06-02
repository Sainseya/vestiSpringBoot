package com.example.vestiback.dto;
import com.example.vestiback.model.User;
import lombok.Data;
import java.util.List;

@Data
public class WardrobeDTO {
    private String id;
    private String name;
    private List<User.Wardrobe.Top> tops;
    private List<User.Wardrobe.Bottom> bottoms;
}
