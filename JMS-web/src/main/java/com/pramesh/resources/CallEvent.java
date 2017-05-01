/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.resources;

import com.pramesh.object.UserObj;
import com.pramesh.service.UserService;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author prames
 */
@Path("callEvent")
public class CallEvent {

    @Inject
    UserService userService;

    @POST
    public Response callEventService(UserObj userObj) {
//        UserObj user = new UserObj();
//        user.setName("pramesh");
//        user.setEmail("pra4mesh@gmail.com");
//        user.setContact("984195894");
        return Response.status(Response.Status.OK).entity(userService.createUser(userObj)).build();
    }

}
