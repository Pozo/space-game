package com.github.pozo

class PlayerProperties(var screenCorner: Coordinate, var screenWidth: Int = 0, var screenHeight: Int = 0) {
    fun getCornerX(): Int {
        return screenCorner.x
    }

    fun getCornerY(): Int {
        return screenCorner.y
    }

    fun setScreenCornerX(x: Int) {
        screenCorner.x = x
    }

    fun setScreenCornerY(y: Int) {
        screenCorner.y = y
    }
}
