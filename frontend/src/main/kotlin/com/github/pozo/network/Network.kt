package com.github.pozo.network

import com.github.pozo.model.Model
import org.w3c.dom.MessageEvent
import org.w3c.dom.WebSocket
import org.w3c.dom.events.Event

class Network {
    var socket = WebSocket("ws://localhost.com:8025/")

    init {
        socket.onmessage = {
            onMessage(it)
        }
    }

    fun onMessage(event: Event) {
        if (event is MessageEvent) {
            val data = event.data
            if (data is String) {
                val model = JSON.parse<Model>(data)
            }
        }
    }

    fun send(message: String) {
        socket.send(message)
    }
}
/*
Network.prototype.login = function(userName) {
    var xmlhttp = new XMLHttpRequest();
    var url = "app/login?userName=" + userName;

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            this.initGame();
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
    // TODO remove
    this.initGame();
}
Network.prototype.initGame = function() {
    var clientCornerCoordinate = playerProperties.getScreenCorner();
    var clientCornerModelCoordinate = clientCornerCoordinate.asModelCoordinate();

    this.socket.send(JSON.stringify({
        "eventType": "LOGIN",
        "eventSubject": {
            "playerId": {
                "id": userName
            }
        },
        "eventData": {
            "screenWidth": playerProperties.getScreenWidth(),
            "screenHeight": playerProperties.getScreenHeight(),
            "screenPosition": {
                "x": clientCornerModelCoordinate.getX(),
                "y": clientCornerModelCoordinate.getY()
            }
        }
    }));
}

 */