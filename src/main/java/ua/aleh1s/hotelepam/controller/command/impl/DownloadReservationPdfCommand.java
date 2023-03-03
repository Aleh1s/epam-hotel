package ua.aleh1s.hotelepam.controller.command.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.aleh1s.hotelepam.appcontext.AppContext;
import ua.aleh1s.hotelepam.appcontext.ResourcesManager;
import ua.aleh1s.hotelepam.controller.command.Command;
import ua.aleh1s.hotelepam.service.PdfBuilderService;
import ua.aleh1s.hotelepam.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadReservationPdfCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PdfBuilderService pdfBuilderService = AppContext.getInstance().getPdfBuilderService();

        Long reservationId = Utils.getLongValue(request, "reservationId");

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", String.format("attachment; filename=reservation_%d.pdf", reservationId));

        try (ByteArrayOutputStream baos = pdfBuilderService.buildReservationPdfById(reservationId);
             OutputStream out = response.getOutputStream()) {
            baos.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResourcesManager.getInstance().getValue("path.command.my.bookings");
    }
}
