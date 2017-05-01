/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.object;

import javax.enterprise.context.RequestScoped;

/**
 *
 * @author prames
 */
@RequestScoped
public class UserObj {
    
    private String name;
    private String email;
    private String contact;
    
    public UserObj() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "name: " + this.name + " email: " + this.email + " contact: " + this.contact;
    }
}
