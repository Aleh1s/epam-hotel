package ua.aleh1s.hotelepam.service;

import ua.aleh1s.hotelepam.exception.ServiceException;
import ua.aleh1s.hotelepam.utils.Mail;

public interface MailService {
    void send(Mail mail) throws ServiceException;
}
