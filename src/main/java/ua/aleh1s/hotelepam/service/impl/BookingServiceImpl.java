package ua.aleh1s.hotelepam.service.impl;

import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.*;
import ua.aleh1s.hotelepam.utils.Mail;
import ua.aleh1s.hotelepam.service.MailService;
import ua.aleh1s.hotelepam.service.*;
import ua.aleh1s.hotelepam.utils.Period;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.UUID;

import static ua.aleh1s.hotelepam.model.entity.ReservationStatus.PENDING_CONFIRMATION;
import static ua.aleh1s.hotelepam.utils.Utils.isReservationPeriodValid;

@AllArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomService roomService;
    private final ReservationService reservationService;
    private final ReservationTokenService reservationTokenService;
    private final MailService mailService;
    private final UserService userService;
    private final RequestService requestService;

    @Override
    public ReservationEntity bookRoom(Integer roomNumber, Long customerId, Period requestedPeriod) throws ServiceException {
        if (!isReservationPeriodValid(requestedPeriod))
            throw new ServiceException("Invalid range of date");

        RoomEntity room = roomService.getByRoomNumber(roomNumber);
        if (!roomService.isRoomAvailable(roomNumber, requestedPeriod))
            throw new ServiceException("Room is unavailable, try pick another room");

        UserEntity user = userService.getById(customerId);
        BigDecimal totalAmount = roomService.getTotalPrice(room, requestedPeriod);

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiredAt = now.plusMinutes(15);

        ReservationEntity reservation = ReservationEntity.builder()
                .roomNumber(roomNumber)
                .customerId(customerId)
                .checkIn(requestedPeriod.start())
                .checkOut(requestedPeriod.end())
                .createdAt(now)
                .expiredAt(expiredAt)
                .totalAmount(totalAmount)
                .status(PENDING_CONFIRMATION)
                .build();
        Long reservationId = reservationService.create(reservation);

        String tokenId = UUID.randomUUID().toString();
        ReservationTokenEntity reservationToken = ReservationTokenEntity.builder()
                .id(tokenId)
                .createdAt(now)
                .expiredAt(expiredAt)
                .reservationId(reservationId)
                .build();
        reservationTokenService.create(reservationToken);

        String message = buildEmail(
                String.format("%s %s", user.getFirstName(), user.getLastName()),
                String.format("http://localhost:8080/controller?command=confirmBooking&tokenId=%s", tokenId), //todo: replace localhost by configured path
                user.getLocale()
        );
        Mail mail = new Mail(
                "Book confirmation",
                message,
                user.getEmail()
        );

        mailService.send(mail);

        return reservation;
    }

    @Override
    public ReservationEntity bookRoom(Long requestId) throws ServiceException {
        RequestEntity request = requestService.getById(requestId);
        return bookRoom(
                request.getRoomNumber(),
                request.getCustomerId(),
                Period.between(
                        request.getCheckIn(),
                        request.getCheckOut()
                )
        );
    }

    private String buildEmail(String name, String link, Locale lang) {
        ResourceBundle bundle = ResourceBundle.getBundle("locale", lang);
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">" + bundle.getString("mail.booking.title") + "</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">" + bundle.getString("mail.booking.greeting") + " " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> " + bundle.getString("mail.booking.gratitude") + ": </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">" + bundle.getString("mail.booking.confirm") + "</a> </p></blockquote>\n " + bundle.getString("mail.booking.link.expire") + " <p>" + bundle.getString("mail.booking.see.you.soon") + "</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

}
