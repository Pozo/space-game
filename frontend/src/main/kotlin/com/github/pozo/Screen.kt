package com.github.pozo

class Screen(var screenCorner: Coordinate,
             var screenWidth: Int = 0,
             var screenHeight: Int = 0,
             var scale: Double = 1.0) {

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

    fun setScale(deltaY: Double) {
        if (deltaY < 0) {
            if (scale > 1) {
                scale -= 0.5
            }
        } else {
            scale += 0.5
        }
    }
}
