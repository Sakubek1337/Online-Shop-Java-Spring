package com.example.demo.Service;

import com.example.demo.Models.Entities.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Delivery {

    @Autowired
    JavaMailSender mailSender;

    public void sendOrder(String email, Quote quote){
        SimpleMailMessage message = new SimpleMailMessage();
        String subject = "Quote delivery from Quotes.shop :)";
        String sb = "Here's your " + quote.getType().toLowerCase() + " quote, enjoy\uD83D\uDE01: \n\n\"" +
                quote.getText() + "\"\n\n― " + quote.getAuthor() + ".\n\nHave a nice day!";
        message.setFrom("quote.shop@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(sb);
        mailSender.send(message);
    }
}
