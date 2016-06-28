package com.github.pozo.game.client.resources;

import com.codahale.metrics.annotation.Timed;
import com.github.pozo.game.client.auth.UserService;
import com.github.pozo.game.client.core.UnprocessableEntityResponse;

import org.glassfish.grizzly.http.CookiesBuilder;
import org.glassfish.jersey.message.internal.NewCookieProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * Created by pozo on 2015.10.05..
 */
@Path("/login")
@Produces(MediaTypeWithEncoding.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    public static final String QUERY_PARAM_USER_NAME = "userName";
    public static final String TOKEN_FOR_TEST = "a9aidiucdha";
    public static final String DOMAIN = ".localhost.com";
    public static final String COOKIE_NAME = "auth-token";

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Timed
    public Response loginUser(@QueryParam(QUERY_PARAM_USER_NAME) String name) {
        try {
            if(validName(name)) {
                Cookie cookie = new Cookie(COOKIE_NAME, TOKEN_FOR_TEST,"/", DOMAIN);
                NewCookie authToken = new NewCookie(cookie);

                return Response.ok().cookie(authToken).build();
            } else {
                throw new WebApplicationException(String.format("'%s' parameter should not be empty!", QUERY_PARAM_USER_NAME));
            }
        } catch(Exception exception) {
            return UnprocessableEntityResponse.getResponse(exception);
        }

    }
    private boolean validName(String name) {
        return name!= null && !name.equals("");
    }
}
