package ua.aleh1s.hotelepam.model.dto;

public record RoomDto(
        Integer number,
        String clazz,
        String title,
        String description,
        String[] attributes,
        Integer beds,
        Integer guests,
        Double price,
        Integer area,
        Boolean isUnavailable
) { }