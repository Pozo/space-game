package com.github.pozo.game.client.core;

import javax.ws.rs.core.Response;

/**
 * Created by pozo on 2015.10.08..
 */
public class UnprocessableEntityResponse {
    public static final int STATUS_CODE = 422;

    private UnprocessableEntityResponse() {
    }

    public static Response getResponse(Exception e) {
        return Response.status(STATUS_CODE).entity(new Error(e.getMessage())).build();
    }
}
