function ShipView(shipModel) {
    this.shipModel = shipModel;
}
ShipView.prototype.shape = new Image();
ShipView.prototype.shape.src =
    "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAAAbtJREFUaIHtmLFKA0EQhj9FCKJRgxFRrMXCMoWKnSDiI4T4DoLYpbXyMQQfQUSwC5oipYVaihhCEqIoiCKcxa3ceGZzl91IPLMfLPx7tzOZYSe7twsOh8PhcDj6x5CBzSSQUfoNqFrGMAeklG4BT5b+IikCnmqVHvirCH/Fbo2HexBAX0l8Aib/gWlgRul7vpfRKn4ddyIDXIp+DlhQug40DWIyJkdQvx6QjWGTDdnkbAJIfAmNGNhsAttKz4beHQCvEfajof4eUFP6BDgziKkr5DLa6zZ4y2jcEloBppReFM+fgQvR34jh8wM4F/01IC18byn9CJRjxhdJifZTHt6JG5pxsjVCNhXNuFKcwBJfQolPQLcTj+PX918iDbyEHyZ+BhKfQNxldB+4UjoP7PxOOBwBx0ovA4dRBnETKBMsa1YfXxHcAqdK/6j3dgxMCemoE0y5KXmC80XX2CZwB+xa+ljHIoF/W0IecCP68hu/Kd7VgCWNjyrBFckEMK8Zd42/cX75lr8pY/A09laEj5SyFcS4Qodxg32kTHwCJtcqkhT+1WA7GgSb0Rj6leYBeLeMw+FwOBwOR1/4BLkGe+ZGLzPtAAAAAElFTkSuQmCC";

ShipView.prototype.selected = false;

ShipView.prototype.setCoordinate = function(newCoordinate) {
    var clientCoordinate = new ClientCoordinate(newCoordinate.x, newCoordinate.y);
    this.shipModel.setCoordinate(clientCoordinate);
}
ShipView.prototype.getCoordinate = function() {
    return this.shipModel.getCoordinate();
}
ShipView.prototype.getCenterCoordinate = function() {
    var clientCornerCoordinate = playerProperties.getScreenCorner();

    return new ClientCoordinate(
        (this.shipModel.getCoordinate().getX() - clientCornerCoordinate.getX()),
        (this.shipModel.getCoordinate().getY() - clientCornerCoordinate.getY())
    );
}
ShipView.prototype.getScreenCoordinate = function() {
    var clientCornerCoordinate = playerProperties.getScreenCorner();

    return new ClientCoordinate(
        (this.shipModel.getCoordinate().getX() - this.shape.width / 2) - clientCornerCoordinate.getX(),
        (this.shipModel.getCoordinate().getY() - this.shape.height / 2) - clientCornerCoordinate.getY()
    );
}
ShipView.prototype.isOnShape = function(coordinate) {
    var screenCoordinate = this.getCenterCoordinate();

    var topLeftCorner = screenCoordinate.getX() - this.shape.width / 2;
    var topRightCorner = screenCoordinate.getX() + this.shape.width / 2;

    var bottomLeftCorner = screenCoordinate.getY() - this.shape.height / 2;
    var bottomRightCorner = screenCoordinate.getY() + this.shape.height / 2;

    var betweenX = coordinate.getX() > topLeftCorner && coordinate.getX() < topRightCorner;
    var betweenY = coordinate.getY() > bottomLeftCorner && coordinate.getY() < bottomRightCorner;

    return betweenX && betweenY;
}
ShipView.prototype.getShape = function() {
    return this.shape;
}
ShipView.prototype.getName = function() {
    return this.shipModel.getName();
}
ShipView.prototype.getDestinations = function() {
    return this.shipModel.getDestinations();
}
ShipView.prototype.setSelected = function(selected) {
    this.selected = selected;
}
ShipView.prototype.isSelected = function() {
    return this.selected;
}
ShipView.prototype.getWidth = function() {
    return this.shape.width;
}
ShipView.prototype.getHeight = function() {
    return this.shape.height;
}
ShipView.prototype.hasDestination = function() {
    return this.shipModel.getDestinations().length > 0;
}
