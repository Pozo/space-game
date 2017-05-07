package com.github.pozo.paint

import com.github.pozo.Screen
import org.w3c.dom.CanvasRenderingContext2D

class BoardPainter(val context: CanvasRenderingContext2D, val screen: Screen) : Painter {
    private var gridSize = 42

    fun draw() {
        val clientCornerCoordinate = screen.screenCorner
//        var clientCornerModelCoordinate = clientCornerCoordinate.asModelCoordinate()

        context.fillText(("x : " + clientCornerCoordinate.x), 30.0, 30.0)
        context.fillText(("y : " + clientCornerCoordinate.y), 30.0, 40.0)

        context.beginPath()

        val offsetX = screen.getCornerX() % gridSize * screen.scale
        val offsetY = screen.getCornerY() % gridSize * screen.scale

        for (x in 0..screen.screenWidth step ((gridSize * screen.scale).toInt())) {
            context.fillText((x - offsetX).toString(), x - offsetX, 10.0)
            context.moveTo(x - offsetX, 0.0)
            context.lineTo(x - offsetX, screen.screenHeight.toDouble())
        }
        for (y in 0..screen.screenHeight step ((gridSize * screen.scale).toInt())) {
            context.fillText((y - offsetY).toString(), 5.0, y - offsetY)
            context.moveTo(0.0, y - offsetY)
            context.lineTo(screen.screenWidth.toDouble(), y - offsetY)
        }

        context.strokeStyle = "black"
        context.lineWidth = 0.2
        context.stroke()

        context.closePath()
    }
}