package ua.aleh1s.hotelepam.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class RoomEntity {
    private Integer roomNumber;
    private RoomClass roomClass;
    private RoomStatus status;
    private String description;
    private LocalDate busyUntil;
    private BigDecimal price;
    private String name;
    private String[] attributes;
    private Integer bedsNumber;
    private Integer personsNumber;
    private Integer area;
}
