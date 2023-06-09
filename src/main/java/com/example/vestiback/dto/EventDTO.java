package com.example.vestiback.dto;
import lombok.Data;

import java.util.Date;
@Data
public class EventDTO {
    private String name;
    private Date beginDate;
    private Date endDate;
    private String description;
}
