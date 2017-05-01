/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.producer;

import com.pramesh.qualifier.PropertiesBundle;
import java.util.ResourceBundle;
import javax.enterprise.inject.Produces;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author prames
 */
@Provider
public class PropertiesProducer {

    @Produces
    @PropertiesBundle
    public ResourceBundle getBundle() {
        ResourceBundle bundle = ResourceBundle.getBundle("Properties.Authentication");
        return bundle;
    }
}
