function ClientCoordinate(x, y) {
    this.x = Math.round(x);
    this.y = Math.round(y);
}
ClientCoordinate.prototype.asModelCoordinate = function() {
    return new ClientCoordinate(this.x, -this.y);
}
ClientCoordinate.prototype.getX = function() {
    return this.x;
}
ClientCoordinate.prototype.getY = function() {
    return this.y;
}
