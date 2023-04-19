package ua.aleh1s.hotelepam.model.dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Getter;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
public class RoomDto {
    private Integer number;
    private Integer beds;
    private Integer guests;
    private Integer area;
    private String title;
    private String description;
    private BigDecimal price;
    private Boolean isUnavailable;
    private List<String> attributes;
    private RoomClass clazz;
    private byte[] image;
}