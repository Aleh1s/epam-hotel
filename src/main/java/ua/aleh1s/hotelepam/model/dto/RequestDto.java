package ua.aleh1s.hotelepam.model.dto;

import lombok.Builder;
import lombok.Getter;
import ua.aleh1s.hotelepam.model.entity.RequestStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
public class RequestDto {
    private Long id;
    private Long customerId;
    private Integer roomNumber;
    private RequestStatus status;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal totalAmount;
}
