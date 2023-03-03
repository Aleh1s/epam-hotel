package ua.aleh1s.hotelepam.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.service.PdfBuilderService;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.service.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class PdfBuilderServiceImpl implements PdfBuilderService {

    private static BaseFont baseFont;
    private static final Font titleFont;
    private static final Font cellFont;

    static {
        try {
            baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", "cp1251", BaseFont.EMBEDDED);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        titleFont = new Font(baseFont, 14);
        cellFont = new Font(baseFont, 10);
    }

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final UserService userService;

    public PdfBuilderServiceImpl(
            ReservationService reservationService,
            RoomService roomService,
            UserService userService) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.userService = userService;
    }

    public ByteArrayOutputStream buildReservationPdfById(Long id) {
        ReservationEntity reservation = reservationService.getById(id);
        RoomEntity room = roomService.getByRoomNumber(reservation.getRoomNumber());
        UserEntity user = userService.getById(reservation.getCustomerId());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Document document = new Document();
            baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();

            Paragraph title = new Paragraph(String.format("Reservation #%d Info", id));
            title.setAlignment(Element.ALIGN_CENTER);
            title.setFont(titleFont);
            title.setSpacingAfter(20f);
            document.add(title);

            PdfPTable table = new PdfPTable(4);
            addKeyValue(table, "Room Number:", room.getRoomNumber().toString());
            addKeyValue(table, "Room Class:", room.getRoomClass().name().toLowerCase());

            addKeyValue(table, "Room Price (Per Night):", String.format("$ %s", room.getPrice().toString()));
            addKeyValue(table, "Room Beds:", room.getBedsNumber().toString());

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            addKeyValue(table, "Check-In:", reservation.getCheckIn().format(dateFormatter));
            addKeyValue(table, "Check-Out:", reservation.getCheckOut().format(dateFormatter));

            addKeyValue(table, "Payed At:", reservation.getPayedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            addKeyValue(table, "Room Guests:", room.getPersonsNumber().toString());

            addKeyValue(table, "Total Amount:", String.format("$ %s", reservation.getTotalAmount().toString()));
            addKeyValue(table, "Status:", reservation.getStatus().name().toLowerCase());

            addKeyValue(table, "First Name:", user.getFirstName());
            addKeyValue(table, "Last Name:", user.getLastName());

            addKeyValue(table, "Email:", user.getEmail());
            addKeyValue(table, "Phone:", user.getPhoneNumber());

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return baos;
    }

    private void addKeyValue(PdfPTable table, String key, String value) {
        Stream.of(key, value).forEach(str -> {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(10f);
            cell.setBorderWidth(0f);
            cell.setPhrase(new Phrase(str, cellFont));
            table.addCell(cell);
        });
    }
}
