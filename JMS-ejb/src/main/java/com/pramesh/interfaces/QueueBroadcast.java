/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.interfaces;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

/**
 *
 * @author prames
 */
@Local
public interface QueueBroadcast {
    
    @Asynchronous
    public void sendEmail(String to, String subject, String messageBody);
    
}
