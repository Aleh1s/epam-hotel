package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;

import java.io.ByteArrayOutputStream;

public interface PdfBuilderService {

    ByteArrayOutputStream buildReservationPdfById(Long id) throws ServiceException;

}
