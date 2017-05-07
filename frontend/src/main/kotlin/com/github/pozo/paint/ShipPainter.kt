package com.github.pozo.paint

import com.github.pozo.Screen
import com.github.pozo.model.ship.ShipView
import org.w3c.dom.CanvasRenderingContext2D

class ShipPainter(val context: CanvasRenderingContext2D, val screen: Screen) : Painter {
    private val imagePath: String = "ship.png"

    fun draw(shipView: ShipView) {
        val image = getImage(imagePath)
        val dx = shipView.x.toDouble() * screen.scale
        val dy = shipView.y.toDouble() * screen.scale
        val dw = 25.0 * screen.scale
        val dh = 25.0 * screen.scale

        context.drawImage(image, dx, dy, dw, dh)
    }
}
/*
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
*/