package com.airlines.mvc.service;

import javax.mail.MessagingException;

public interface MailService {
    void sendMail(String to, String subject, String text, boolean isHtmlContent, byte[] attachment) throws MessagingException;
}
