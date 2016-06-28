var enabled = false;
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

var controlEdgeWidth = 100;
var controlEdgeStepSize = 10;

var currentSection = Sections.UNDEFINED;
var previousSection = Sections.UNDEFINED;

var intervalID;

var resetSections = function(event) {
    if (enabled) {
        currentSection = Sections.UNDEFINED;
        previousSection = Sections.UNDEFINED;

        clearInterval(intervalID);
    }

}
var sectionController = function(event) {
    if (enabled) {
        var x = event.pageX,
            y = event.pageY;
        var mousePosition = new ClientCoordinate(x, y);

        setCurrentSection(mousePosition);

        if (currentSection == Sections.CENTER) {
            clearInterval(intervalID);
        }

        if (previousSection != currentSection) {
            previousSection = currentSection;

            if (currentSection != Sections.CENTER) {
                clearInterval(intervalID);
                intervalID = setInterval(function() {
                    var offsetY = controlEdgeStepSize * scale;
                    var offsetX = controlEdgeStepSize * scale;

                    if (currentSection == Sections.TOP) {
                        corner.y -= offsetY;
                    } else if (currentSection == Sections.TOP_LEFT) {
                        corner.x -= offsetX;
                        corner.y -= offsetY;
                    } else if (currentSection == Sections.TOP_RIGHT) {
                        corner.x += offsetX;
                        corner.y -= offsetY;
                    } else if (currentSection == Sections.LEFT) {
                        corner.x -= offsetX;
                    } else if (currentSection == Sections.RIGHT) {
                        corner.x += offsetX;
                    } else if (currentSection == Sections.BOTTOM) {
                        corner.y += offsetY;
                    } else if (currentSection == Sections.BOTTOM_LEFT) {
                        corner.x -= offsetX;
                        corner.y += offsetY;
                    } else if (currentSection == Sections.BOTTOM_RIGHT) {
                        corner.x += offsetX;
                        corner.y += offsetY;
                    }

                    drawScene();
                }, 20);
            }
        }
    }
}

function setCurrentSection(mousePosition) {
    if (onTop(mousePosition)) {
        if (onLeft(mousePosition)) {
            currentSection = Sections.TOP_LEFT;
        } else if (onRight(mousePosition)) {
            currentSection = Sections.TOP_RIGHT;
        } else {
            currentSection = Sections.TOP;
        }
    } else if (onBottom(mousePosition)) {
        if (onLeft(mousePosition)) {
            currentSection = Sections.BOTTOM_LEFT;
        } else if (onRight(mousePosition)) {
            currentSection = Sections.BOTTOM_RIGHT;
        } else {
            currentSection = Sections.BOTTOM;
        }
    } else if (onLeft(mousePosition)) {
        currentSection = Sections.LEFT;
    } else if (onRight(mousePosition)) {
        currentSection = Sections.RIGHT;
    } else {
        currentSection = Sections.CENTER;
    }
}

function onLeft(coordinate) {
    return coordinate.x <= controlEdgeWidth;
}

function onRight(coordinate) {
    return coordinate.x >= width - controlEdgeWidth;
}

function onTop(coordinate) {
    return coordinate.y <= controlEdgeWidth;
}

function onBottom(coordinate) {
    return coordinate.y >= height - controlEdgeWidth;
}
