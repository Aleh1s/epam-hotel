package ua.aleh1s.hotelepam.utils;

public record Mail(
        String subject,
        String message,
        String toAddress
) { }
