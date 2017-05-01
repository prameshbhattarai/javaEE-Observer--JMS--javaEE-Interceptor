/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.implement;

import com.pramesh.interfaces.EmailSender;
import com.pramesh.object.EmailObject;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.pramesh.qualifier.Email;
import com.pramesh.qualifier.PropertiesBundle;
import java.util.ResourceBundle;

/**
 *
 * @author prames
 */
@RequestScoped
public class EmailSenderImpl implements EmailSender {

    @Inject
    @Email
    Session session;
    
    @Inject
    @PropertiesBundle
    private ResourceBundle bundle;

    /**
     * method to send Email using Gmail Session
     * @param emailObject
     */
    @Override
    public void sendEmail(EmailObject emailObject) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");
            message.setFrom(new InternetAddress(bundle.getString("from")));
            message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(emailObject.getTo()));
            message.setSubject(emailObject.getSubject());
//            message.setText(emailObject.getBody());
            message.setContent(emailObject.getBody(), "text/html");
            Transport.send(message);
        } catch(MessagingException me) {
            System.out.println("-------------------------------------------");
            System.out.println("Email Sender :: Unable to create message ");
            System.out.println("-------------------------------------------");
            me.printStackTrace();
            
        } catch (Exception e) {
            System.out.println("--------------------------------");
            System.out.println("Email Sender :: Unable to send message ");
            System.out.println("--------------------------------");
            e.printStackTrace();
        }
    }

}
