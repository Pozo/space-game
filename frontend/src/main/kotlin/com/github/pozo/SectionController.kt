package com.github.pozo

import org.w3c.dom.events.MouseEvent
import kotlin.browser.window

enum class Sections {
    UNDEFINED,
    TOP,
    TOP_LEFT,
    TOP_RIGHT,

    RIGHT,
    LEFT,

    BOTTOM,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,

    CENTER
}

class SectionController(val screen: Screen, val canvas: Canvas) {
    var enabled = false
    var controlEdgeWidth = 100
    var controlEdgeStepSize = 10

    var currentSection = Sections.UNDEFINED
    var previousSection = Sections.UNDEFINED

    var intervalID: Int = 0
    var interval = 30

    fun resetSections() {
        if (enabled) {
            currentSection = Sections.UNDEFINED
            previousSection = Sections.UNDEFINED

            window.clearInterval(intervalID)
        }
    }

    fun control(event: MouseEvent) {
        if (enabled) {
            val x = event.pageX
            val y = event.pageY
            val mousePosition = Coordinate(x.toInt(), y.toInt())

            setCurrentSection(mousePosition)

            if (currentSection == Sections.CENTER) {
                window.clearInterval(intervalID)
            }

            if (previousSection != currentSection) {
                previousSection = currentSection

                if (currentSection != Sections.CENTER) {
                    window.clearInterval(intervalID)
                    intervalID = window.setInterval({
                        val screenCorner = screen.screenCorner
                        val currentX = screenCorner.x
                        val currentY = screenCorner.y

                        val offsetY = controlEdgeStepSize * screen.scale
                        val offsetX = controlEdgeStepSize * screen.scale

                        if (currentSection == Sections.TOP) {
                            screen.setScreenCornerY((currentY - offsetY).toInt())
                        } else if (currentSection == Sections.TOP_LEFT) {
                            screen.setScreenCornerX((currentX - offsetX).toInt())
                            screen.setScreenCornerY((currentY - offsetY).toInt())
                        } else if (currentSection == Sections.TOP_RIGHT) {
                            screen.setScreenCornerX((currentX + offsetX).toInt())
                            screen.setScreenCornerY((currentY - offsetY).toInt())
                        } else if (currentSection == Sections.LEFT) {
                            screen.setScreenCornerX((currentX - offsetX).toInt())
                        } else if (currentSection == Sections.RIGHT) {
                            screen.setScreenCornerX((currentX + offsetX).toInt())
                        } else if (currentSection == Sections.BOTTOM) {
                            screen.setScreenCornerY((currentY + offsetY).toInt())
                        } else if (currentSection == Sections.BOTTOM_LEFT) {
                            screen.setScreenCornerX((currentX - offsetX).toInt())
                            screen.setScreenCornerY((currentY + offsetY).toInt())
                        } else if (currentSection == Sections.BOTTOM_RIGHT) {
                            screen.setScreenCornerX((currentX + offsetX).toInt())
                            screen.setScreenCornerY((currentY + offsetY).toInt())
                        }
                        canvas.drawScene()
                    }, interval)
                }
            }
        }
    }

    fun setCurrentSection(mousePosition: Coordinate) {
        if (onTop(mousePosition)) {
            if (onLeft(mousePosition)) {
                currentSection = Sections.TOP_LEFT
            } else if (onRight(mousePosition)) {
                currentSection = Sections.TOP_RIGHT
            } else {
                currentSection = Sections.TOP
            }
        } else if (onBottom(mousePosition)) {
            if (onLeft(mousePosition)) {
                currentSection = Sections.BOTTOM_LEFT
            } else if (onRight(mousePosition)) {
                currentSection = Sections.BOTTOM_RIGHT
            } else {
                currentSection = Sections.BOTTOM
            }
        } else if (onLeft(mousePosition)) {
            currentSection = Sections.LEFT
        } else if (onRight(mousePosition)) {
            currentSection = Sections.RIGHT
        } else {
            currentSection = Sections.CENTER
        }
    }

    fun onLeft(coordinate: Coordinate): Boolean {
        return coordinate.x <= controlEdgeWidth
    }

    fun onRight(coordinate: Coordinate): Boolean {
        return coordinate.x >= screen.screenWidth - controlEdgeWidth
    }

    fun onTop(coordinate: Coordinate): Boolean {
        return coordinate.y <= controlEdgeWidth
    }

    fun onBottom(coordinate: Coordinate): Boolean {
        return coordinate.y >= screen.screenHeight - controlEdgeWidth
    }
}


