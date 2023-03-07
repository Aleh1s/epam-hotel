package ua.aleh1s.hotelepam.model.dto;

import java.time.LocalDate;

public record RoomDto(
        Integer roomNumber,
        String roomClass,
        String roomStatus,
        String description,
        LocalDate busyUntil,
        Double price,
        String name,
        String[] attributes,
        Integer bedsNumber,
        Integer personsNumber,
        Integer area
) { }