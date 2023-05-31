package com.example.vestiback.dto;
import com.example.vestiback.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class EventDTO {
    private String id;
    private Date beginDate;
    private Date endDate;
    private String description;
    private List<ItemDTO> outfit;
}
