package ua.aleh1s.hotelepam.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RoomEntity {
    private Integer number;
    private RoomClass clazz;
    private String title;
    private String description;
    private List<String> attributes;
    private Integer beds;
    private Integer guests;
    private Integer area;
    private BigDecimal price;
    private Boolean isUnavailable;
    private byte[] image;
}
