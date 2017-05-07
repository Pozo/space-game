package com.github.pozo.board

import com.github.pozo.Screen
import com.github.pozo.model.Coordinate
import com.github.pozo.model.ship.Ship
import com.github.pozo.model.ship.ShipPresenter
import com.github.pozo.model.ship.ShipView
import com.github.pozo.paint.BoardPainter
import com.github.pozo.paint.ShipPainter
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import kotlin.browser.window
import kotlin.js.Math.abs


class Canvas(val screen: Screen) {
    val canvas: HTMLCanvasElement = kotlin.browser.document.getElementById("canvas") as HTMLCanvasElement
    val context: CanvasRenderingContext2D = canvas.getContext("2d")!! as CanvasRenderingContext2D
    private val sectionController = SectionController(screen, this)

    private val boardPainter: BoardPainter = BoardPainter(context, screen)
    private val shipPainter: ShipPainter = ShipPainter(context, screen)

    private var drag: Boolean = false
    var mouseDownCoordinate = Coordinate(0, 0)
    var mouseDownCoordinateWithCornerOffset = Coordinate(0, 0)

    private var ship: Ship
    private var shipView: ShipView
    var shipPresenter: ShipPresenter

    init {
        ship = Ship(Coordinate(0, 0))
        shipView = ShipView(ship)
        shipPresenter = ShipPresenter(ship, shipView)

        canvas.width = window.innerWidth
        canvas.height = window.innerHeight

        canvas.onmousemove = { evt: Event ->
            onMouseMove(evt)
        }
        canvas.onmouseleave = { evt: Event ->
            onMouseLeave(evt)
        }
        canvas.onmousedown = { evt: Event ->
            onMouseDown(evt)
        }
        canvas.onmouseup = { event: Event ->
            onMouseUp(event)
        }
        canvas.oncontextmenu = { evt ->
            evt.preventDefault()
        }
        canvas.onwheel = { evt ->
            onWheel(evt)
        }
    }

    private fun onMouseMove(evt: Event) {
        evt as MouseEvent
        sectionController.control(evt)

        val mousePosition = Coordinate(evt.pageX.toInt(), evt.pageY.toInt())

        if (drag) {
            screen.setScreenCornerX(mouseDownCoordinateWithCornerOffset.x - mousePosition.x)
            screen.setScreenCornerY(mouseDownCoordinateWithCornerOffset.y - mousePosition.y)

            drawScene()
        }
    }

    private fun onMouseLeave(evt: Event) {
        evt as MouseEvent
        drag = false
        sectionController.resetSections()
    }

    private fun onMouseDown(evt: Event) {
        evt as MouseEvent

        if (evt.buttons == 1.toShort()) {
            val x = evt.pageX
            val y = evt.pageY

            val mousePosition = Coordinate(x.toInt(), y.toInt())
            val clientCornerCoordinate = screen.screenCorner
            mouseDownCoordinate.x = mousePosition.x
            mouseDownCoordinate.y = mousePosition.y

            mouseDownCoordinateWithCornerOffset.x = mouseDownCoordinate.x + clientCornerCoordinate.x
            mouseDownCoordinateWithCornerOffset.y = mouseDownCoordinate.y + clientCornerCoordinate.y

            drag = true
        }
    }

    private fun onMouseUp(event: Event) {
        event as MouseEvent
        drag = false

        if (event.button == 0.toShort()) {
            val x = event.pageX
            val y = event.pageY

            val mouseUpCoordinate = Coordinate(x.toInt(), y.toInt())

            val movedOnX = abs((mouseDownCoordinate.x - mouseUpCoordinate.x).toDouble()) == 0.toDouble()
            val movedOnY = abs((mouseDownCoordinate.y - mouseUpCoordinate.y).toDouble()) == 0.toDouble()

            shipPresenter.moveTo(mouseUpCoordinate)

            if (movedOnX && movedOnY) {

                drawScene()
            }

        }
    }

    fun onWheel(evt: Event) {
        evt as WheelEvent

        screen.setScale(evt.deltaY)

        drawScene()
    }

    fun drawScene() {
        context.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        boardPainter.draw()
        shipPainter.draw(shipView)
    }

    fun resizeCanvas() {
        canvas.width = window.innerWidth
        canvas.height = window.innerHeight

        screen.screenWidth = canvas.width
        screen.screenHeight = canvas.height
    }
}


