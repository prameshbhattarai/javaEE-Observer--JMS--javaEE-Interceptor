/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.interceptor;

import javax.interceptor.Interceptor;
import com.pramesh.annotation.PersistUnit;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.pramesh.annotation.Database;

/**
 *
 * @author prames
 */
@Interceptor
@PersistUnit
public class PersistUnitInterceptor {

    @PersistenceContext(unitName = "observer-PU")
    private EntityManager observerEm;

    @AroundInvoke
    public Object methodInterceptor(InvocationContext ctx) throws Exception {
        System.out.println("------------------------");
        System.out.println("Intercepted by Persitence Unit Interceptor");
        System.out.println("------------------------");
        applyPerisitenceUnit(ctx);
        try {
            return ctx.proceed();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    protected void applyPerisitenceUnit(InvocationContext ctx) {

        if (ctx.getMethod().isAnnotationPresent(Database.class)) {
            Database database = ctx.getMethod().getAnnotation(Database.class);
            String databaseName = database.name();
            if (databaseName.equalsIgnoreCase("database1")) {
                Object[] params = ctx.getParameters();
                params[0] = observerEm;
                System.out.println("Injected observer-PU");
                ctx.setParameters(params);
            }
        }
    }
    
}    
