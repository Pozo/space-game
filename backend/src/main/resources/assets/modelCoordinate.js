function ModelCoordinate(x, y) {
    this.x = x;
    this.y = y;
}
ModelCoordinate.prototype.asClientCoordinate = function() {
    return new ClientCoordinate(this.x, -this.y);
}
ModelCoordinate.prototype.getX = function() {
    return this.x;
}
ModelCoordinate.prototype.getY = function() {
    return this.y;
}
