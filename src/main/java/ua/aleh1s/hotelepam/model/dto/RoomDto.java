package ua.aleh1s.hotelepam.model.dto;

import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.util.List;

public record RoomDto(
        Integer number,
        RoomClass clazz,
        String title,
        String description,
        List<String> attributes,
        Integer beds,
        Integer guests,
        Double price,
        Integer area,
        Boolean isUnavailable
) { }