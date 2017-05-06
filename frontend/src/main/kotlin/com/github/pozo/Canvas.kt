package com.github.pozo

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.MessageEvent
import org.w3c.dom.WebSocket
import org.w3c.dom.events.Event
import org.w3c.dom.events.MouseEvent
import org.w3c.dom.events.WheelEvent
import kotlin.browser.window
import kotlin.js.Math.abs


class Canvas(val screen: Screen) {
    val canvas: HTMLCanvasElement = kotlin.browser.document.getElementById("canvas") as HTMLCanvasElement
    val context: CanvasRenderingContext2D = canvas.getContext("2d")!! as CanvasRenderingContext2D
    val sectionController = SectionController(screen, this)
    val boardPainter: BoardPainter = BoardPainter(screen)

    private var drag: Boolean = false
    private var line: Int = 0

    var mouseDownCoordinate = Coordinate(0, 0)
    var mouseDownCoordinateWithCornerOffset = Coordinate(0, 0)

    init {
        canvas.width = window.innerWidth
        canvas.height = window.innerHeight
    }


    fun connectAndDraw() {
        drawScene()

        //context.translate((canvas.width / 2).toDouble(), (canvas.height / 2).toDouble())

        val socket = WebSocket("ws://localhost.com:8025/")
        socket.onmessage = {
            onMessage(socket, it)
        }
        canvas.onmousemove = { evt: Event ->
            evt as MouseEvent
            sectionController.control(evt)

            val mousePosition = Coordinate(evt.pageX.toInt(), evt.pageY.toInt());

            if (drag) {
                screen.setScreenCornerX(mouseDownCoordinateWithCornerOffset.x - mousePosition.x);
                screen.setScreenCornerY(mouseDownCoordinateWithCornerOffset.y - mousePosition.y);

                drawScene()
            }
        }
        canvas.onmouseleave = { evt: Event ->
            evt as MouseEvent
            drag = false
            sectionController.resetSections()
        }
        canvas.onmousedown = { evt: Event ->
            evt as MouseEvent

            if (evt.buttons == 1.toShort()) {
                val x = evt.pageX
                val y = evt.pageY

                val mousePosition = Coordinate(x.toInt(), y.toInt());
                val clientCornerCoordinate = screen.screenCorner
                mouseDownCoordinate.x = mousePosition.x;
                mouseDownCoordinate.y = mousePosition.y;

                mouseDownCoordinateWithCornerOffset.x = mouseDownCoordinate.x + clientCornerCoordinate.x
                mouseDownCoordinateWithCornerOffset.y = mouseDownCoordinate.y + clientCornerCoordinate.y

                drag = true;
                println(Coordinate(evt.pageX.toInt(), evt.pageY.toInt()))
            }
        }
        canvas.onmouseup = { event: Event ->
            event as MouseEvent
            drag = false

            if (event.button == 0.toShort()) {
                val x = event.pageX
                val y = event.pageY

                val mouseUpCoordinate = Coordinate(x.toInt(), y.toInt());

                val movedOnX = abs((mouseDownCoordinate.x - mouseUpCoordinate.x).toDouble()) == 0.toDouble()
                val movedOnY = abs((mouseDownCoordinate.y - mouseUpCoordinate.y).toDouble()) == 0.toDouble()

                if (movedOnX && movedOnY) {
                    println("movedOnX && movedOnY")
//                    for (var i = 0; i < ships.length; i++) {
//                        var ship = ships[i];
//                        if (ship.isOnShape(mouseUpCoordinate)) {
//                            ship.setSelected(!ship.isSelected());
//                        } else {
//                            ship.setSelected(false);
//                        }
//
//                    }
                    drawScene()
                }

            }
        }
        canvas.oncontextmenu = { evt ->
            evt.preventDefault()
        }
        canvas.onwheel = { evt ->
            evt as WheelEvent

            screen.setScale(evt.deltaY)

            drawScene()
        }
    }

    fun drawScene() {
        context.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        boardPainter.drawBoard(context)
    }

    fun onMessage(ws: WebSocket, event: Event) {
        if (event is MessageEvent) {
            val data = event.data

            if (data is String) {
                context.font = "12px sans-serif"
                context.fillText(data, 0.0, (context.font.size * line++).toDouble())
            }
        }
    }

    fun resizeCanvas() {
        canvas.width = window.innerWidth
        canvas.height = window.innerHeight

        screen.screenWidth = canvas.width
        screen.screenHeight = canvas.height
        drawScene()
    }
}


