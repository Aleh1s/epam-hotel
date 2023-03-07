package ua.aleh1s.hotelepam.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class RequestEntity {
    private Long id;
    private Integer roomNumber;
    private Long customerId;
    private RequestStatus status;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BigDecimal totalAmount;
}
