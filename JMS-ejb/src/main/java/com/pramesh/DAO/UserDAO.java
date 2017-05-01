/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.DAO;

import com.pramesh.annotation.Database;
import com.pramesh.annotation.PersistUnit;
import com.pramesh.entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author prames
 */
@Stateless
public class UserDAO {

    @PersistUnit
    @Database(name = "database1")
    public Boolean save(Object object, User user) {
        try {
            EntityManager em = (EntityManager) object;
            em.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
