package ua.aleh1s.hotelepam.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ReservationTokenEntity {

    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
    private Long reservationId;

    private ReservationTokenEntity(
            String id,
            LocalDateTime createdAt,
            LocalDateTime expiredAt,
            LocalDateTime confirmedAt,
            Long reservationId) {
        this.id = id;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.confirmedAt = confirmedAt;
        this.reservationId = reservationId;
    }

    public static class Builder {
        private String id;
        private LocalDateTime createdAt;
        private LocalDateTime expiredAt;
        private LocalDateTime confirmedAt;
        private Long reservationId;

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder expiredAt(LocalDateTime expiredAt) {
            this.expiredAt = expiredAt;
            return this;
        }

        public Builder confirmedAt(LocalDateTime confirmedAt) {
            this.confirmedAt = confirmedAt;
            return this;
        }

        public Builder reservationId(Long reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public ReservationTokenEntity build() {
            return new ReservationTokenEntity(
                    id, createdAt, expiredAt, confirmedAt, reservationId
            );
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationTokenEntity that = (ReservationTokenEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(expiredAt, that.expiredAt) && Objects.equals(confirmedAt, that.confirmedAt) && Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, expiredAt, confirmedAt, reservationId);
    }

    @Override
    public String toString() {
        return "ReservationTokenEntity{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", expiredAt=" + expiredAt +
                ", confirmedAt=" + confirmedAt +
                ", reservationId=" + reservationId +
                '}';
    }
}
