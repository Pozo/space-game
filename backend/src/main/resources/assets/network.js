function Network() {
    this.socket = new WebSocket("ws://localhost.com:8025/");
    this.socket.onopen = this.onopen.bind(this);
    this.socket.onmessage = this.onmessage;
}

Network.prototype.onopen = function(event) {
    console.log(userName);
    this.login(userName)
}
Network.prototype.onmessage = function(event) {
    var gameEvent = JSON.parse(event.data);

    if ("UPDATE" == gameEvent.eventType) {
        model.clearModel();

        var astronomicalObjects = gameEvent.eventData.astronomicalObjects;
        var artificialObjects = gameEvent.eventData.artificialObjects;

        for (var key in artificialObjects) {
            if (artificialObjects.hasOwnProperty(key)) {
                if (Types.SHIP == key) {

                    var ships = artificialObjects[key];
                    var moldelShips = model.getShips();

                    for (var i = 0; i < ships.length; i++) {
                        var ship = new Ship(ships[i].id, ships[i].coord);
                        ship.setDestinations(ships[i].route);

                        moldelShips.push(ship);
                    }
                }
            }
        }
        // TODO wrap
        currentTime = 0;
    } else if ("TARGET_REACHED" == gameEvent.eventType) {
        var subject = gameEvent.eventData;
        var newCoordinate = gameEvent.eventData.coord;

        console.log('Your ship ' + subject.id + ' reached ' + subject.coord.x + ' and ' + subject.coord.y);
    } else if ("STOPPED" == gameEvent.eventType) {
        var subject = gameEvent.eventData.id;
        console.log('Your ship ' + subject + ' stopped');
    }
}
Network.prototype.socket;

Network.prototype.move = function(ship, shipTargetCoordinate) {
    this.socket.send(JSON.stringify({
        "eventType": "MOVE",
        "eventSubject": {
            "id": ship.getName()
        },
        "eventData": {
            "x": shipTargetCoordinate.getX(),
            "y": shipTargetCoordinate.getY()
        }
    }));
}
Network.prototype.changePlayerPreferences = function() {
    playerProperties.setScreenWidth(canvas.width);
    playerProperties.setScreenHeight(canvas.height);

    if (this.socket && this.socket.readyState == WebSocket.OPEN) {
        var clientCornerCoordinate = playerProperties.getScreenCorner();
        var clientCornerModelCoordinate = clientCornerCoordinate.asModelCoordinate();

        this.socket.send(JSON.stringify({
            "eventType": "CHANGE_PREFERENCES",
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
}
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
