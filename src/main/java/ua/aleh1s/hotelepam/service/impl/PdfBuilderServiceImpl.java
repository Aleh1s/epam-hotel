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
import java.util.ResourceBundle;
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

        ResourceBundle bundle = ResourceBundle.getBundle("locale", user.getLocale());

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
            addKeyValue(table, bundle.getString("room.number"), room.getRoomNumber().toString());
            addKeyValue(table, bundle.getString("room.class"), room.getRoomClass().name().toLowerCase());

            addKeyValue(table, bundle.getString("room.price.per.night"), String.format("$ %s", room.getPrice().toString()));
            addKeyValue(table, bundle.getString("beds"), room.getBedsNumber().toString());

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            addKeyValue(table, bundle.getString("check.in"), reservation.getCheckIn().format(dateFormatter));
            addKeyValue(table, bundle.getString("check.out"), reservation.getCheckOut().format(dateFormatter));

            addKeyValue(table, bundle.getString("payed.at"), reservation.getPayedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            addKeyValue(table, bundle.getString("guests"), room.getPersonsNumber().toString());

            addKeyValue(table, bundle.getString("total.amount"), String.format("$ %s", reservation.getTotalAmount().toString()));
            addKeyValue(table, bundle.getString("status"), reservation.getStatus().name().toLowerCase());

            addKeyValue(table, bundle.getString("first.name"), user.getFirstName());
            addKeyValue(table, bundle.getString("last.name"), user.getLastName());

            addKeyValue(table, bundle.getString("email"), user.getEmail());
            addKeyValue(table, bundle.getString("phone"), user.getPhoneNumber());

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
