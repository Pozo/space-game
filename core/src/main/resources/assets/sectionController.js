function SectionController() {

}
var Sections = {
    UNDEFINED: -1,

    TOP: 1,
    TOP_LEFT: 2,
    TOP_RIGHT: 3,

    RIGHT: 4,
    LEFT: 5,

    BOTTOM: 6,
    BOTTOM_LEFT: 7,
    BOTTOM_RIGHT: 8,

    CENTER: 9,
};
SectionController.prototype.enabled = false;
SectionController.prototype.controlEdgeWidth = 100;
SectionController.prototype.controlEdgeStepSize = 10;

SectionController.prototype.currentSection = Sections.UNDEFINED;
SectionController.prototype.previousSection = Sections.UNDEFINED;

SectionController.prototype.intervalID;
SectionController.prototype.interval = 30;

SectionController.prototype.resetSections = function() {
    if (this.enabled) {
        this.currentSection = Sections.UNDEFINED;
        this.previousSection = Sections.UNDEFINED;

        clearInterval(this.intervalID);
    }
}
SectionController.prototype.sectionController = function(event) {
    if (this.enabled) {
        var x = event.pageX,
            y = event.pageY;
        var mousePosition = new ClientCoordinate(x, y);

        this.setCurrentSection(mousePosition);

        if (this.currentSection == Sections.CENTER) {
            clearInterval(this.intervalID);
        }

        if (this.previousSection != this.currentSection) {
            this.previousSection = this.currentSection;

            if (this.currentSection != Sections.CENTER) {
                clearInterval(this.intervalID);
                this.intervalID = setInterval(function() {
                    var screenCorner = playerProperties.getScreenCorner();
                    var currentX = screenCorner.getX();
                    var currentY = screenCorner.getY();

                    var offsetY = this.controlEdgeStepSize * scale;
                    var offsetX = this.controlEdgeStepSize * scale;

                    if (this.currentSection == Sections.TOP) {
                        playerProperties.setScreenCornerY(currentY - offsetY);
                    } else if (this.currentSection == Sections.TOP_LEFT) {
                        playerProperties.setScreenCornerX(currentX - offsetX);
                        playerProperties.setScreenCornerY(currentY - offsetY);
                    } else if (this.currentSection == Sections.TOP_RIGHT) {
                        playerProperties.setScreenCornerX(currentX + offsetX);
                        playerProperties.setScreenCornerY(currentY - offsetY);
                    } else if (this.currentSection == Sections.LEFT) {
                        playerProperties.setScreenCornerX(currentX - offsetX);
                    } else if (this.currentSection == Sections.RIGHT) {
                        playerProperties.setScreenCornerX(currentX + offsetX);
                    } else if (this.currentSection == Sections.BOTTOM) {
                        playerProperties.setScreenCornerY(currentY + offsetY);
                    } else if (this.currentSection == Sections.BOTTOM_LEFT) {
                        playerProperties.setScreenCornerX(currentX - offsetX);
                        playerProperties.setScreenCornerY(currentY + offsetY);
                    } else if (this.currentSection == Sections.BOTTOM_RIGHT) {
                        playerProperties.setScreenCornerX(currentX + offsetX);
                        playerProperties.setScreenCornerY(currentY + offsetY);
                    }

                    drawer.drawScene();
                }, this.interval);
            }
        }
    }
}
SectionController.prototype.setCurrentSection = function(mousePosition) {
    if (this.onTop(mousePosition)) {
        if (this.onLeft(mousePosition)) {
            this.currentSection = Sections.TOP_LEFT;
        } else if (this.onRight(mousePosition)) {
            this.currentSection = Sections.TOP_RIGHT;
        } else {
            this.currentSection = Sections.TOP;
        }
    } else if (this.onBottom(mousePosition)) {
        if (this.onLeft(mousePosition)) {
            this.currentSection = Sections.BOTTOM_LEFT;
        } else if (this.onRight(mousePosition)) {
            this.currentSection = Sections.BOTTOM_RIGHT;
        } else {
            this.currentSection = Sections.BOTTOM;
        }
    } else if (this.onLeft(mousePosition)) {
        this.currentSection = Sections.LEFT;
    } else if (this.onRight(mousePosition)) {
        this.currentSection = Sections.RIGHT;
    } else {
        this.currentSection = Sections.CENTER;
    }
}
SectionController.prototype.onLeft = function(coordinate) {
    return coordinate.x <= this.controlEdgeWidth;
}

SectionController.prototype.onRight = function(coordinate) {
    return coordinate.x >= playerProperties.getScreenWidth() - this.controlEdgeWidth;
}

SectionController.prototype.onTop = function(coordinate) {
    return coordinate.y <= this.controlEdgeWidth;
}

SectionController.prototype.onBottom = function(coordinate) {
    return coordinate.y >= playerProperties.getScreenHeight() - this.controlEdgeWidth;
}
