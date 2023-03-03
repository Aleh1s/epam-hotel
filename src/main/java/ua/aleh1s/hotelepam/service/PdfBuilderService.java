package ua.aleh1s.hotelepam.service;

import java.io.ByteArrayOutputStream;

public interface PdfBuilderService {

    ByteArrayOutputStream buildReservationPdfById(Long id);

}
