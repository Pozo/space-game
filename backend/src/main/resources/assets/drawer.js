function Drawer() {

}
Drawer.prototype.drawScene = function() {
    context.clearRect(0, 0, canvas.width, canvas.height);

    this.drawBoard();
    this.drawShips();
}
Drawer.prototype.drawBoard = function() {
    var clientCornerCoordinate = playerProperties.getScreenCorner();
    var clientCornerModelCoordinate = clientCornerCoordinate.asModelCoordinate();

    context.fillText('x : ' + clientCornerModelCoordinate.getX(), 30, 30);
    context.fillText('y : ' + clientCornerModelCoordinate.getY(), 30, 40);

    context.beginPath();

    var offsetX = clientCornerCoordinate.getX() % gridSize * scale;
    var offsetY = clientCornerCoordinate.getY() % gridSize * scale;

    for (var x = 0; x <= playerProperties.getScreenWidth(); x += gridSize * scale) {
        context.fillText(x - offsetX, x - offsetX, 10);
        context.moveTo(x - offsetX, 0);
        context.lineTo(x - offsetX, playerProperties.getScreenHeight());
    }
    for (var y = 0; y <= playerProperties.getScreenHeight(); y += gridSize * scale) {
        context.fillText(y - offsetY, 5, y - offsetY);
        context.moveTo(0, y - offsetY);
        context.lineTo(playerProperties.getScreenWidth(), y - offsetY);
    }
    context.strokeStyle = "black";
    context.lineWidth = 0.2;
    context.stroke();

    context.closePath();
}

Drawer.prototype.drawShips = function() {
    var ships = model.getShips();
    var previousShips = model.getPreviousShips();

    if (ships) {
        for (var i = 0; i < ships.length; i++) {
            var ship = ships[i];
            var previousShip = previousShips[i];
            var shipCurrentCoordinate = ship.getScreenCoordinate();
            var previousShipCoordinate = previousShip.getScreenCoordinate();

            var initialX = previousShipCoordinate.getX();
            var initialY = previousShipCoordinate.getY();

            var finalX = shipCurrentCoordinate.getX();
            var finalY = shipCurrentCoordinate.getY();

            var finalTime = 1000;
            var percentage = currentTime / finalTime;

            var interpolatedX = this.interpolate(initialX, finalX, percentage);
            var interpolatedY = this.interpolate(initialY, finalY, percentage);

            this.drawShipRoute(ship);
            this.drawShipSelection(ship);

            context.beginPath();
            context.drawImage(ship.getShape(), interpolatedX, interpolatedY);
            context.closePath();

        }
    }

}
Drawer.prototype.interpolate = function(initialNumber, finalNumber, progress) {
    return initialNumber + (finalNumber - initialNumber) * progress;
}
Drawer.prototype.drawShipSelection = function(ship) {
    if (ship.isSelected()) {
        var shipCurrentCoordinate = ship.getScreenCoordinate();
        context.beginPath();
        context.arc(shipCurrentCoordinate.x + ship.getWidth() / 2, shipCurrentCoordinate.y + ship.getHeight() / 2, 30, 0, 2 * Math.PI, false);
        context.lineWidth = 2;
        context.strokeStyle = 'blue';
        context.stroke();
        context.closePath();
    }

}

Drawer.prototype.drawShipRoute = function(ship) {
    if (ship.hasDestination()) {
        var shipCurrentCoordinate = ship.getCenterCoordinate();
        var destinations = ship.getDestinations();

        var previousDestination;
        for (var i = 0; i < destinations.length; i++) {
            var destination = destinations[i];
            context.beginPath();
            var startX, startY, destinationX, destinationY, rotation;

            var clientCornerCoordinate = playerProperties.getScreenCorner();

            if (previousDestination) {
                startX = previousDestination.getX() - clientCornerCoordinate.getX();
                startY = previousDestination.getY() - clientCornerCoordinate.getY();

                destinationX = destination.getX() - clientCornerCoordinate.getX();
                destinationY = destination.getY() - clientCornerCoordinate.getY();
            } else {
                startX = shipCurrentCoordinate.getX();
                startY = shipCurrentCoordinate.getY();

                destinationX = destination.getX() - clientCornerCoordinate.getX();
                destinationY = destination.getY() - clientCornerCoordinate.getY();
            }
            context.moveTo(startX, startY);
            context.lineTo(destinationX, destinationY);
            context.lineWidth = 3;
            context.strokeStyle = 'grey';
            context.stroke();

            var rotation = -Math.atan2(startX - destinationX, startY - destinationY);
            this.arrowHead(destinationX, destinationY, rotation);
            previousDestination = destination;
            context.closePath();
        }

    }
}
Drawer.prototype.arrowHead = function(x, y, rotation) {
    context.save();

    context.translate(x, y);
    context.rotate(rotation + Math.PI);
    context.beginPath();
    context.moveTo(0, 0);
    context.lineTo(-5, -12);
    context.lineTo(5, -12);
    context.closePath();
    context.fillStyle = 'grey';
    context.fill();
    context.strokeStyle = 'grey';
    context.stroke();

    context.restore();
}
