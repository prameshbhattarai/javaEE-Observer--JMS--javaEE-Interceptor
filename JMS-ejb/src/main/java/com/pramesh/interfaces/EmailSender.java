/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.interfaces;

import com.pramesh.object.EmailObject;

/**
 *
 * @author prames
 */
public interface EmailSender {
    
    public void sendEmail(EmailObject emailObject);
    
}
