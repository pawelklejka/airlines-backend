package com.airlines.mvc.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class MailServiceImpl implements MailService {
    private JavaMailSender javaMailSender;

    public MailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendMail(String to, String subject, String text, boolean isHtmlContent, byte[] attachment) throws MessagingException {
        File tempFile = null;
        try {
            tempFile = Files.createTempFile("ticket", "pdf").toFile();

            Files.write(tempFile.toPath(), attachment);


        } catch (IOException e) {
            e.printStackTrace();
        }
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, isHtmlContent);
        mimeMessageHelper.addAttachment("ticket.pdf", tempFile);
        javaMailSender.send(mimeMessage);
        tempFile.deleteOnExit();
    }
}
