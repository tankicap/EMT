package com.example.lab1b.model.dto;

import com.example.lab1b.model.Category;
import lombok.Data;

@Data
public class HousingDto {
    private String name;
    private Category category;
    private Long hostId;
    private Integer numRooms;
}
