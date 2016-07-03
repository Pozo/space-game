function Ship(name, coordinate) {
    this.name = name;
    this.setCoordinate(coordinate);
}
Ship.prototype.shape = new Image();
Ship.prototype.shape.src =
    "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAAAbtJREFUaIHtmLFKA0EQhj9FCKJRgxFRrMXCMoWKnSDiI4T4DoLYpbXyMQQfQUSwC5oipYVaihhCEqIoiCKcxa3ceGZzl91IPLMfLPx7tzOZYSe7twsOh8PhcDj6x5CBzSSQUfoNqFrGMAeklG4BT5b+IikCnmqVHvirCH/Fbo2HexBAX0l8Aib/gWlgRul7vpfRKn4ddyIDXIp+DlhQug40DWIyJkdQvx6QjWGTDdnkbAJIfAmNGNhsAttKz4beHQCvEfajof4eUFP6BDgziKkr5DLa6zZ4y2jcEloBppReFM+fgQvR34jh8wM4F/01IC18byn9CJRjxhdJifZTHt6JG5pxsjVCNhXNuFKcwBJfQolPQLcTj+PX918iDbyEHyZ+BhKfQNxldB+4UjoP7PxOOBwBx0ovA4dRBnETKBMsa1YfXxHcAqdK/6j3dgxMCemoE0y5KXmC80XX2CZwB+xa+ljHIoF/W0IecCP68hu/Kd7VgCWNjyrBFckEMK8Zd42/cX75lr8pY/A09laEj5SyFcS4Qodxg32kTHwCJtcqkhT+1WA7GgSb0Rj6leYBeLeMw+FwOBwOR1/4BLkGe+ZGLzPtAAAAAElFTkSuQmCC";

Ship.prototype.currentCoordinate;
Ship.prototype.currentRelativeCoordinate;
Ship.prototype.previousCoordinate;

Ship.prototype.selected = false;

Ship.prototype.destinations = [];

Ship.prototype.setCoordinate = function(newCoordinate) {
    var modelCoordinate = new ModelCoordinate(newCoordinate.x, newCoordinate.y);
    this.previousCoordinate = this.currentCoordinate;
    this.currentCoordinate = modelCoordinate.asClientCoordinate();
}
Ship.prototype.getCoordinate = function() {
    return this.currentCoordinate;
}
Ship.prototype.getCenterCoordinate = function() {
    var clientCornerCoordinate = playerProperties.getScreenCorner();

    return new ClientCoordinate(
        (this.currentCoordinate.getX() - clientCornerCoordinate.getX()),
        (this.currentCoordinate.getY() - clientCornerCoordinate.getY())
    );
}
Ship.prototype.getScreenCoordinate = function() {
    var clientCornerCoordinate = playerProperties.getScreenCorner();

    return new ClientCoordinate(
        (this.currentCoordinate.getX() - this.shape.width / 2) - clientCornerCoordinate.getX(),
        (this.currentCoordinate.getY() - this.shape.height / 2) - clientCornerCoordinate.getY()
    );
}
Ship.prototype.isOnShape = function(coordinate) {
    var screenCoordinate = this.getCenterCoordinate();

    var topLeftCorner = screenCoordinate.getX() - this.shape.width / 2;
    var topRightCorner = screenCoordinate.getX() + this.shape.width / 2;

    var bottomLeftCorner = screenCoordinate.getY() - this.shape.height / 2;
    var bottomRightCorner = screenCoordinate.getY() + this.shape.height / 2;

    var betweenX = coordinate.getX() > topLeftCorner && coordinate.getX() < topRightCorner;
    var betweenY = coordinate.getY() > bottomLeftCorner && coordinate.getY() < bottomRightCorner;

    return betweenX && betweenY;
}
Ship.prototype.getShape = function() {
    return this.shape;
}
Ship.prototype.setName = function(name) {
    this.name = name;
}
Ship.prototype.getName = function() {
    return this.name;
}
Ship.prototype.getPreviousCoordinate = function() {
    return this.previousCoordinate;
}
Ship.prototype.addDestination = function(destination) {
    var modelCoordinate = new ModelCoordinate(destination.x, destination.y);
    this.destinations.push(modelCoordinate.asClientCoordinate());
}
Ship.prototype.getDestinations = function() {
    return this.destinations;
}
Ship.prototype.setDestinations = function(destinations) {
    this.destinations = [];

    for (var destination in destinations) {
        if (destinations.hasOwnProperty(destination)) {
            this.addDestination(destinations[destination]);
        }
    }

}
Ship.prototype.setSelected = function(selected) {
    this.selected = selected;
}
Ship.prototype.isSelected = function() {
    return this.selected;
}
Ship.prototype.getWidth = function() {
    return this.shape.width;
}
Ship.prototype.getHeight = function() {
    return this.shape.height;
}
Ship.prototype.hasDestination = function() {
    return this.destinations.length > 0;
}
