package com.github.pozo.game.server;

import java.util.List;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Created by pozo on 2016.06.19..
 */
public class GameServerConfigurator extends ServerEndpointConfig.Configurator {
    private static final String TOKEN_NAME = "auth-token";

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        List<String> cookieList = request.getHeaders().get("Cookie");


        if (null == cookieList) {
            throw new IllegalArgumentException("Unauthenticated user");
        }
        for (String cookie : cookieList) {
            String[] cookieKeyValue = cookie.split("=");

            if (TOKEN_NAME.equals(cookieKeyValue[0])) {
                System.out.println("token = " + cookieKeyValue[1]);
            }
        }
        super.modifyHandshake(sec, request, response);
    }

}
