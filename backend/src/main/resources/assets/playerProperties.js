function PlayerProperties(screenCorner, screenWidth, screenHeight) {
    this.setScreenCorner(screenCorner);
    this.setScreenWidth(screenWidth);
    this.setScreenHeight(screenHeight);
}
PlayerProperties.prototype.getScreenCorner = function(screenCorner) {
    return this.screenCorner;
}
PlayerProperties.prototype.setScreenCorner = function(screenCorner) {
    this.screenCorner = screenCorner;
}

PlayerProperties.prototype.getScreenCornerX = function() {
    return this.screenCorner.x;
}
PlayerProperties.prototype.setScreenCornerX = function(screenCornerX) {
    this.screenCorner.x = screenCornerX;
}

PlayerProperties.prototype.getScreenCornerY = function() {
    return this.screenCorner.y;
}
PlayerProperties.prototype.setScreenCornerY = function(screenCornerY) {
    this.screenCorner.y = screenCornerY;
}

PlayerProperties.prototype.getScreenWidth = function() {
    return this.screenWidth;
}
PlayerProperties.prototype.setScreenWidth = function(screenWidth) {
    this.screenWidth = screenWidth;
}

PlayerProperties.prototype.getScreenHeight = function() {
    return this.screenHeight;
}
PlayerProperties.prototype.setScreenHeight = function(screenHeight) {
    this.screenHeight = screenHeight;
}

PlayerProperties.prototype.getUserName = function(username) {
    return this.userName;
}
PlayerProperties.prototype.setUserName = function(username) {
    this.userName = userName;
}
