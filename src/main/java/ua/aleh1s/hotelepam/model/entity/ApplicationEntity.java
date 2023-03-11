package ua.aleh1s.hotelepam.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
public class ApplicationEntity {
    private Long id;
    private Integer guests;
    private RoomClass roomClass;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDateTime createdAt;
    private ApplicationStatus status;
    private Long customerId;
}