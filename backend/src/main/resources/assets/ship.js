function Ship(name, coordinate) {
    this.setName(name);
    this.setCoordinate(coordinate);
}

Ship.prototype.currentCoordinate;
Ship.prototype.destinations = [];

Ship.prototype.setCoordinate = function(newCoordinate) {
    var modelCoordinate = new ModelCoordinate(newCoordinate.x, newCoordinate.y);
    this.currentCoordinate = modelCoordinate.asClientCoordinate();
}
Ship.prototype.getCoordinate = function() {
    return this.currentCoordinate;
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

Ship.prototype.addDestination = function(destination) {
    var modelCoordinate = new ModelCoordinate(destination.x, destination.y);
    this.destinations.push(modelCoordinate.asClientCoordinate());
}
Ship.prototype.getDestinations = function() {
    return this.destinations;
}
Ship.prototype.hasDestination = function() {
    return this.destinations.length > 0;
}
Ship.prototype.setDestinations = function(destinations) {
    this.destinations = [];

    for (var destination in destinations) {
        if (destinations.hasOwnProperty(destination)) {
            this.addDestination(destinations[destination]);
        }
    }

}
