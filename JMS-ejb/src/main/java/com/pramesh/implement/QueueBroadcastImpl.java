/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.implement;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import com.pramesh.interfaces.QueueBroadcast;

/**
 * 
 * @author prames
 */
@Stateless
public class QueueBroadcastImpl implements QueueBroadcast {

    @Resource(mappedName = "jms/emailQueue")
    Queue queue;

    @Inject
    @JMSConnectionFactory("jms/__defaultConnectionFactory")
    JMSContext jmsContext;

    /**
     * method to set JMS ObjectMessage and send it to Queue resource
     * @param to emailAddress
     * @param subject emailSubject
     * @param messageBody emailBody
     */
    @Override
    public void sendEmail(String to, String subject, String messageBody) {
        try {
            ObjectMessage createObjectMessage = jmsContext.createObjectMessage();
            try {
                createObjectMessage.setStringProperty("to", to);
                createObjectMessage.setStringProperty("subject", subject);
                createObjectMessage.setStringProperty("body", messageBody);
            } catch (Exception e) {
                System.out.println("----------------------------------------");
                System.out.println("Queue :: Unable to set objectMessage properties");
                System.out.println("----------------------------------------");
                e.printStackTrace();
            }
            jmsContext.createProducer().send(queue, createObjectMessage);
        } catch (Exception e) {
            System.out.println("--------------------------------");
            System.out.println("Queue :: Unable to createObject Message ");
            System.out.println("--------------------------------");
            e.printStackTrace();
        }
    }

}
