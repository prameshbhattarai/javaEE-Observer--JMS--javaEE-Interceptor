/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.service;

import com.pramesh.object.Responses;
import com.pramesh.object.UserObj;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.JsonObject;

/**
 *
 * @author prames
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserService {

    @Inject
    Event<UserObj> userEvent;

    /**
     * fire event for creating user
     * Observer event return void, so we will not be able get response
     * @param user
     * @return json responses
     */
    public JsonObject createUser(UserObj user) {
        try {
            userEvent.fire(user);
            return Responses.createResponse("User registered successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return Responses.errorResponse(e.getMessage());
        }
    }

}
