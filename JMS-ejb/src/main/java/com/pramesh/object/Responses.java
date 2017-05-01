/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pramesh.object;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

/**
 *
 * @author pramesh.bhattarai
 */
public class Responses {

    public static JsonObject createResponse(String message) {
        JsonObject response;
        response = Json.createObjectBuilder()
                .add("message", message)
                .add("statusCode", Response.Status.CREATED.getStatusCode())
                .build();
        return response;
    }

    public static JsonObject errorResponse(String message) {
        JsonObject response;
        response = Json.createObjectBuilder()
                .add("message", message)
                .add("statusCode", Response.Status.EXPECTATION_FAILED.getStatusCode())
                .build();
        return response;
    }
}
