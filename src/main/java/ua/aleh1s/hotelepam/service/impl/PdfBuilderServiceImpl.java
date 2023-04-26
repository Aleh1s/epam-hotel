package ua.aleh1s.hotelepam.service.impl;

import com.itextpdf.awt.geom.Rectangle;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.model.entity.ReservationEntity;
import ua.aleh1s.hotelepam.model.entity.RoomEntity;
import ua.aleh1s.hotelepam.model.entity.UserEntity;
import ua.aleh1s.hotelepam.service.PdfBuilderService;
import ua.aleh1s.hotelepam.service.ReservationService;
import ua.aleh1s.hotelepam.service.RoomService;
import ua.aleh1s.hotelepam.service.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.stream.Stream;

@AllArgsConstructor
public class PdfBuilderServiceImpl implements PdfBuilderService {

    private static BaseFont baseFont;
    private static final Font h1Bold;
    private static final Font h2Bold;
    private static final Font pFont;
    private static final Font pFontBold;
    private static final Font pFontItalic;

    static {
        try {
            baseFont = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", "cp1251", BaseFont.EMBEDDED);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
        h1Bold = new Font(baseFont, 14, Font.BOLD);
        h2Bold = new Font(baseFont, 12, Font.BOLD);
        pFontItalic = new Font(baseFont, 10, Font.ITALIC);
        pFontBold = new Font(baseFont, 10, Font.BOLD);
        pFont = new Font(baseFont, 10);
    }

    private final ReservationService reservationService;
    private final RoomService roomService;
    private final UserService userService;

    public ByteArrayOutputStream buildReservationPdfById(Long id) throws ServiceException {
        ReservationEntity reservation = reservationService.getById(id);
        RoomEntity room = roomService.getByRoomNumber(reservation.getRoomNumber());
        UserEntity user = userService.getById(reservation.getCustomerId());

        ResourceBundle bundle = ResourceBundle.getBundle("locale", user.getLocale());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            Document document = new Document(PageSize.A4, 25f, 25f, 35f, 35f);
            baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            document.open();

            Paragraph title = new Paragraph(bundle.getString("booking.info"), h1Bold);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);

            PdfPTable headerTable = new PdfPTable(4);
            headerTable.setWidthPercentage(100f);
            headerTable.setSpacingAfter(5f);

            PdfPCell address = new PdfPCell();
            address.setHorizontalAlignment(Element.ALIGN_CENTER);
            address.setBorderWidth(0);
            address.setPhrase(new Phrase("Via Tammaricella 127", pFontItalic));
            headerTable.addCell(address);

            PdfPCell phone = new PdfPCell();
            phone.setHorizontalAlignment(Element.ALIGN_CENTER);
            phone.setBorderWidth(0);
            phone.setPhrase(new Phrase("+380 (68)-663-2241", pFontItalic));
            headerTable.addCell(phone);

            PdfPCell email = new PdfPCell();
            email.setHorizontalAlignment(Element.ALIGN_CENTER);
            email.setBorderWidth(0);
            email.setPhrase(new Phrase("hotel@gmail.com", pFontItalic));
            headerTable.addCell(email);

            PdfPCell site = new PdfPCell();
            site.setHorizontalAlignment(Element.ALIGN_CENTER);
            site.setBorderWidth(0);
            site.setPhrase(new Phrase("www.hotel.com", pFontItalic));
            headerTable.addCell(site);

            document.add(headerTable);

            PdfPTable headerLineTable = new PdfPTable(1);
            headerLineTable.setWidthPercentage(100f);
            headerLineTable.setSpacingAfter(20f);

            PdfPCell headerLineCell = new PdfPCell();
            headerLineCell.setBorderWidth(0);
            headerLineCell.setBorderWidthBottom(4f);
            headerLineCell.setBorderColorBottom(new BaseColor(52, 86, 139, 125));
            headerLineTable.addCell(headerLineCell);

            document.add(headerLineTable);

            Paragraph bookingDetailsTitle = new Paragraph(bundle.getString("booking.details"), h2Bold);
            bookingDetailsTitle.setAlignment(Element.ALIGN_LEFT);
            bookingDetailsTitle.setSpacingAfter(5f);
            document.add(bookingDetailsTitle);
            PdfPTable bookingDetailsTable = new PdfPTable(2);
            bookingDetailsTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            bookingDetailsTable.setSpacingAfter(20f);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            addKeyValue(bookingDetailsTable, new Phrase(bundle.getString("check.in"), pFont), new Phrase(reservation.getCheckIn().format(dateFormatter), pFontBold));
            addKeyValue(bookingDetailsTable, new Phrase(bundle.getString("check.out"), pFont), new Phrase(reservation.getCheckOut().format(dateFormatter), pFontBold));
            addKeyValue(bookingDetailsTable, new Phrase(bundle.getString("guests"), pFont), new Phrase(room.getGuests().toString(), pFont));
            addKeyValue(bookingDetailsTable, new Phrase(bundle.getString("room.class"), pFont), new Phrase(room.getClazz().name().toLowerCase(), pFont));
            addKeyValue(bookingDetailsTable, new Phrase(bundle.getString("payed.at"), pFont), new Phrase(reservation.getPayedAt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), pFont));
            document.add(bookingDetailsTable);

            Paragraph bookedByTitle = new Paragraph(bundle.getString("booked.by"), h2Bold);
            bookedByTitle.setAlignment(Element.ALIGN_LEFT);
            bookedByTitle.setSpacingAfter(5f);
            document.add(bookedByTitle);
            PdfPTable bookedByTable = new PdfPTable(2);
            bookedByTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            bookedByTable.setSpacingAfter(20f);
            addKeyValue(bookedByTable, new Phrase(bundle.getString("full.name"), pFont), new Phrase(String.format("%s %s", user.getFirstName(), user.getLastName()), pFont));
            addKeyValue(bookedByTable, new Phrase(bundle.getString("email"), pFont), new Phrase(user.getEmail(), pFont));
            addKeyValue(bookedByTable, new Phrase(bundle.getString("phone"), pFont), new Phrase(user.getPhoneNumber(), pFont));
            document.add(bookedByTable);

            PdfPTable priceTable = new PdfPTable(2);
            priceTable.setWidthPercentage(90f);
            priceTable.setHorizontalAlignment(Element.ALIGN_CENTER);
            priceTable.setSpacingAfter(20f);
            DecimalFormat decimalFormat = new DecimalFormat("#0.00");
            Integer nights = (int) ChronoUnit.DAYS.between(reservation.getCheckIn(), reservation.getCheckOut());
            addCell(priceTable,
                    new Phrase(bundle.getString("description"), pFontItalic),
                    new Phrase(bundle.getString("amount"), pFontItalic),
                    new BaseColor(52, 86, 139, 125));
            addCell(priceTable,
                    new Phrase(String.format("%d x %s ($ %s)", nights, bundle.getString("nights"), decimalFormat.format(room.getPrice())), pFont),
                    new Phrase(String.format("$ %s", decimalFormat.format(reservation.getTotalAmount())), pFont),
                    BaseColor.WHITE);
            addCell(priceTable,
                    new Phrase(bundle.getString("total"), pFontItalic),
                    new Phrase(String.format("$ %s", decimalFormat.format(reservation.getTotalAmount())), pFont),
                    new BaseColor(255, 255, 155, 125));
            document.add(priceTable);

            Paragraph additionalInformationTitle = new Paragraph(bundle.getString("additional.information"), h2Bold);
            additionalInformationTitle.setAlignment(Element.ALIGN_LEFT);
            additionalInformationTitle.setSpacingAfter(5f);
            document.add(additionalInformationTitle);

            PdfPTable additionalInformationTable = new PdfPTable(2);
            additionalInformationTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            additionalInformationTable.setSpacingAfter(20f);
            addKeyValue(additionalInformationTable, new Phrase(bundle.getString("check.in.from"), pFont), new Phrase("11:00", pFontBold));
            addKeyValue(additionalInformationTable, new Phrase(bundle.getString("check.out.until"), pFont), new Phrase("11:00", pFontBold));
            document.add(additionalInformationTable);

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return baos;
    }

    private void addKeyValue(PdfPTable table, Phrase key, Phrase value) {
        Stream.of(key, value).forEach(phrase -> {
            PdfPCell cell = new PdfPCell();
            cell.setPadding(4f);
            cell.setBorderWidth(0f);
            cell.setPhrase(phrase);
            table.addCell(cell);
        });
    }

    private void addCell(PdfPTable table, Phrase key, Phrase value, BaseColor color) {
        BaseColor borderColor = new BaseColor(128, 128, 128);

        PdfPCell keyCell = new PdfPCell();
        keyCell.setPadding(10f);
        keyCell.setBorderColor(borderColor);
        keyCell.setBorderWidth(0.5f);
        keyCell.setBackgroundColor(color);
        keyCell.setBorderWidthRight(0f);
        keyCell.setPhrase(key);
        keyCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(keyCell);

        PdfPCell valueCell = new PdfPCell();
        valueCell.setPadding(10f);
        valueCell.setBorderColor(borderColor);
        valueCell.setBorderWidth(0.5f);
        valueCell.setBackgroundColor(color);
        valueCell.setBorderWidthLeft(0f);
        valueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        valueCell.setPhrase(value);
        table.addCell(valueCell);
    }
}