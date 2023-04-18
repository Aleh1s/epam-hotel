package ua.aleh1s.hotelepam.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ua.aleh1s.hotelepam.model.entity.ApplicationStatus;
import ua.aleh1s.hotelepam.model.entity.RoomClass;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class ApplicationDto {
    private Long id;
    private Integer guests;
    private RoomClass roomClass;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private LocalDateTime createdAt;
    private ApplicationStatus status;
    private Long customerId;
}
