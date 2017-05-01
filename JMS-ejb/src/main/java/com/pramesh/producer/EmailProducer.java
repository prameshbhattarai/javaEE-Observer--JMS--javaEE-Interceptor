/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.producer;

import com.pramesh.qualifier.PropertiesBundle;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.ws.rs.ext.Provider;
import com.pramesh.qualifier.Email;

/**
 *
 * @author prames
 */
@Provider
public class EmailProducer {

    @Inject
    @PropertiesBundle
    private ResourceBundle bundle;

    @Produces
    @Email
    public Session session() {
        System.out.println("-------------------------------------------");
        System.out.println("Email Session :: Creating gmail session ");
        System.out.println("-------------------------------------------");
        Properties props = this.getEmailProperties();
        Session session = Session.getInstance(props, this.getAuthenticator());
        return session;
    }

    private Properties getEmailProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return props;
    }

    protected Authenticator getAuthenticator() {
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(bundle.getString("username"), bundle.getString("password"));
            }
        };
        return auth;
    }
}
