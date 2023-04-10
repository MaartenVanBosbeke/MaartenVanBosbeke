package com.shopr.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shopr.domains.Contact;

import lombok.Data;

@Data
@Service
public class ContactService {

    @Autowired
    private JavaMailSender emailSender;


    private SimpleMailMessage simpleMailMessage;

    @Autowired
    private JavaMailSenderImpl sender;

    public void sendMessageWithAttachment(Contact contact, MultipartFile file) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(contact.getEmail());
            helper.setTo("maartenvanbosbeke@gmail.com");
            helper.setSubject("Message from: " + contact.getLastName() + " (last name) " + contact.getFirstName() + " (first name)");
            helper.setText(contact.getMessage());
            helper.addAttachment(file.getOriginalFilename(), file);
        } catch (MessagingException e) {
            throw new MailParseException(e);
        } sender.send(message);
    }

    public void sendMessageWithoutAttachment(Contact contact) {
        MimeMessage message = emailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, false);
            helper.setFrom(contact.getEmail());
            helper.setTo("maartenvanbosbeke@gmail.com");
            helper.setSubject("Message from: " + contact.getLastName() + " (last name) " + contact.getFirstName() + " (first name)");
            helper.setText(contact.getMessage());
        } catch (MessagingException e) {
            throw new MailParseException(e);
        } sender.send(message);
    }
}