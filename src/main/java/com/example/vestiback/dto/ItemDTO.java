package com.example.vestiback.dto;
import com.example.vestiback.model.User;
import lombok.Data;

import java.util.List;

@Data
public class ItemDTO {
    private String id;
    private List<User.Wardrobe.Top> tops;
    private List<User.Wardrobe.Bottom> bottoms;
}
