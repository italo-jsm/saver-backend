package com.example.demo.infrastructure.messaging.mail;

import jakarta.mail.*;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.AndTerm;
import jakarta.mail.search.ComparisonTerm;
import jakarta.mail.search.ReceivedDateTerm;
import jakarta.mail.search.SearchTerm;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class MailIMAPReader {

    public static void main(String[] args) {
        String host = "imap.gmail.com";
        String username = "italojunqueira87@gmail.com";
        String password = "prod apme gaem ulso";

        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imaps.host", host);
        properties.put("mail.imaps.port", "993");
        properties.put("mail.imaps.ssl.enable", "true");

        try {
            Session session = Session.getInstance(properties);
            Store store = session.getStore("imaps");
            store.connect(username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            Calendar cal = Calendar.getInstance();
            Date endDate = cal.getTime(); // Agora

            cal.add(Calendar.HOUR, -1);
            Date startDate = cal.getTime(); // Uma hora atrás

            SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GE, startDate);
            SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LE, endDate);
            SearchTerm dateRange = new AndTerm(newerThan, olderThan);

            Message[] messages = inbox.search(dateRange);

            for (Message message : messages) {
                System.out.println("De: " + message.getFrom()[0]);
                System.out.println("Assunto: " + message.getSubject());
                System.out.println("Data: " + message.getReceivedDate());
                System.out.println("Corpo: " + getTextFromMessage(message));
                System.out.println("----------------------------------------------------");
            }

            inbox.close();
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return mimeMultipart.getBodyPart(0).getContent().toString();
        }
        return "";
    }
}

