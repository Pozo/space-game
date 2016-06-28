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
    this.previousCoordinate = this.currentCoordinate;
    this.currentCoordinate = newCoordinate;
}
Ship.prototype.getCoordinate = function() {
    return this.currentCoordinate;
}
Ship.prototype.getCenterCoordinate = function() {
    return new Coordinate(
        (this.currentCoordinate.x - corner.x),
        (this.currentCoordinate.y - corner.y)
    );
}
Ship.prototype.getScreenCoordinate = function() {
    return new Coordinate(
        (this.currentCoordinate.x - this.shape.width / 2) - corner.x,
        (this.currentCoordinate.y - this.shape.height / 2) - corner.y
    );
}
Ship.prototype.isOnShape = function(coordinate) {
    var screenCoordinate = this.getCenterCoordinate();

    var topLeftCorner = screenCoordinate.x - this.shape.width / 2;
    var topRightCorner = screenCoordinate.x + this.shape.width / 2;

    var bottomLeftCorner = screenCoordinate.y - this.shape.height / 2;
    var bottomRightCorner = screenCoordinate.y + this.shape.height / 2;

    var betweenX = coordinate.x > topLeftCorner && coordinate.x < topRightCorner;
    var betweenY = coordinate.y > bottomLeftCorner && coordinate.y < bottomRightCorner;

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
    this.destinations.push(destination);
}
Ship.prototype.getDestinations = function() {
    return this.destinations;
}
Ship.prototype.setDestinations = function(destinations) {
    this.destinations = destinations;
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
