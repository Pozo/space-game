package com.github.pozo.game.client.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by pozo on 2015.10.05..
 */
public class Error {
    @JsonProperty
    private String errorMessage;

    public Error(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
