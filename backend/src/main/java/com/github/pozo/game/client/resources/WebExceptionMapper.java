package com.github.pozo.game.client.resources;

import org.eclipse.jetty.http.HttpStatus;

import java.util.HashMap;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by pozo on 2015.10.05..
 */
public class WebExceptionMapper implements ExceptionMapper<WebApplicationException> {
    @Override
    public Response toResponse(final WebApplicationException e) {
        // If the message did not come with a status, we'll default to an internal
        // server error status.
        int status = e.getResponse() == null ? 500 : e.getResponse().getStatus();

        // Get a nice human readable message for our status code if the exception
        // doesn't already have a message
        final String msg = e.getMessage() == null ?
                HttpStatus.getMessage(status) : e.getMessage();

        // Create a JSON response with the provided hashmap
        return Response.status(status)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new HashMap<String, String>() {
                    {
                        put("error", msg);
                    }
                }).build();
    }
}
