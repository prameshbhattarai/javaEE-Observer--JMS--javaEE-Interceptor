/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.implement;

import com.pramesh.interfaces.EmailSender;
import com.pramesh.object.EmailObject;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author prames
 */
@MessageDriven(mappedName = "jms/emailQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/emailQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
@ApplicationScoped
public class QueueListenerImpl implements MessageListener {

    @Inject
    EmailSender emailSender;

    /**
     * method to listen JMS ObjectMessage from Queue resource
     * @param message JMS ObjectMessage
     */
    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            EmailObject emailObject = new EmailObject();
            emailObject.setTo(objectMessage.getStringProperty("to"));
            emailObject.setSubject(objectMessage.getStringProperty("subject"));
            emailObject.setBody(objectMessage.getStringProperty("body"));

            emailSender.sendEmail(emailObject);
        } catch (JMSException ex) {
            System.out.println("-------------------------------------------");
            System.out.println("Queue Listener :: Unable to listen queue ");
            System.out.println("-------------------------------------------");
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
