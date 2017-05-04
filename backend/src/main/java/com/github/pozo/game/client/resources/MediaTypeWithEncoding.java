package com.github.pozo.game.client.resources;

import javax.ws.rs.core.MediaType;

/**
 * Created by pozo on 2015.10.11..
 */
public class MediaTypeWithEncoding extends MediaType {
    public static final String APPLICATION_JSON = MediaType.APPLICATION_JSON + "; charset=utf-8";
}
