/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.observer;

import com.pramesh.DAO.UserDAO;
import com.pramesh.entities.User;
import com.pramesh.object.UserObj;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import com.pramesh.interfaces.QueueBroadcast;
import com.pramesh.qualifier.PropertiesBundle;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 *
 * @author prames
 */
@ApplicationScoped
public class UserObserver {

    @Inject
    private UserDAO userDAO;
    
    @Inject
    private QueueBroadcast queueBroadcast;
    
    @Inject
    @PropertiesBundle
    private ResourceBundle bundle;

    /**
     * when event for UserObj will be fire
     * following function will be executed
     * @param userObj is a Observable object
      */
    public void observeUserCreation(@Observes UserObj userObj){
        System.out.println("----------------------------------");
        System.out.println("Listened by User Observer");
        System.out.println("----------------------------------");
        User user = new User();
        setEntity(user, userObj);
        if(userDAO.save(new Object(), user)) {
            String subject = bundle.getString("successSubject");
            String messageBody = MessageFormat.format(bundle.getString("successBody"), userObj.getName());
            queueBroadcast.sendEmail(userObj.getEmail(), subject, messageBody);
        }else {
            String subject = bundle.getString("failureSubject");
            String messageBody = MessageFormat.format(bundle.getString("failureBody"), userObj.getName());
            queueBroadcast.sendEmail(userObj.getEmail(), subject, messageBody);
        }
    }

    /**
     * method for conversion of UserObj to UserEntity
     * @param user entity
     * @param userObj object
     */
    private void setEntity(User user, UserObj userObj) {
        user.setName(userObj.getName());
        user.setEmail(userObj.getEmail());
        user.setContact(userObj.getContact());
    }

}
