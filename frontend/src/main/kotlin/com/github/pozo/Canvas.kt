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


class Canvas(val playerProperties: PlayerProperties) {
    val canvas: HTMLCanvasElement = kotlin.browser.document.getElementById("canvas") as HTMLCanvasElement
    val context: CanvasRenderingContext2D = canvas.getContext("2d")!! as CanvasRenderingContext2D
    val sectionController = SectionController(playerProperties, this)

    private var drag: Boolean = false
    private var line: Int = 0
    private var gridSize = 42
    var scale = 1.0

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
                playerProperties.setScreenCornerX(mouseDownCoordinateWithCornerOffset.x - mousePosition.x);
                playerProperties.setScreenCornerY(mouseDownCoordinateWithCornerOffset.y - mousePosition.y);

                drawScene()
            }
        }
        canvas.onmouseleave = { evt: Event ->
            evt as MouseEvent
            drag = false
            sectionController.resetSections()
        }
        /*
             if (event.buttons == 1) {
                var x = event.pageX,
                    y = event.pageY;
                var mousePosition = new ClientCoordinate(x, y);
                var clientCornerCoordinate = playerProperties.getScreenCorner();
                mouseDownCoordinate.x = mousePosition.x;
                mouseDownCoordinate.y = mousePosition.y;

                mouseDownCoordinateWithCornerOffset.x = mouseDownCoordinate.x + clientCornerCoordinate.getX();
                mouseDownCoordinateWithCornerOffset.y = mouseDownCoordinate.y + clientCornerCoordinate.getY();

                drag = true;
            }
        * */
        canvas.onmousedown = { evt: Event ->
            evt as MouseEvent

            if (evt.buttons == 1.toShort()) {
                val x = evt.pageX
                val y = evt.pageY

                val mousePosition = Coordinate(x.toInt(), y.toInt());
                val clientCornerCoordinate = playerProperties.screenCorner
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

            if (evt.deltaY < 0) {
                if (scale > 1) {
                    scale -= 0.5
                }
            } else {
                scale += 0.5
            }
            drawScene()
        }
    }

    fun drawScene() {
        context.clearRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())
        drawBoard()
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

        playerProperties.screenWidth = canvas.width
        playerProperties.screenHeight = canvas.height
        drawScene()
    }

    fun drawBoard() {
        val clientCornerCoordinate = playerProperties.screenCorner
//        var clientCornerModelCoordinate = clientCornerCoordinate.asModelCoordinate()

        context.fillText(("x : " + clientCornerCoordinate.x), 30.0, 30.0)
        context.fillText(("y : " + clientCornerCoordinate.y), 30.0, 40.0)

        context.beginPath()

        val offsetX = playerProperties.getCornerX() % gridSize * scale
        val offsetY = playerProperties.getCornerY() % gridSize * scale

        for (x in 0..playerProperties.screenWidth step ((gridSize * scale).toInt())) {
            context.fillText((x - offsetX).toString(), x - offsetX, 10.0)
            context.moveTo(x - offsetX, 0.0)
            context.lineTo(x - offsetX, playerProperties.screenHeight.toDouble())
        }
        for (y in 0..playerProperties.screenHeight step ((gridSize * scale).toInt())) {
            context.fillText((y - offsetY).toString(), 5.0, y - offsetY)
            context.moveTo(0.0, y - offsetY)
            context.lineTo(playerProperties.screenWidth.toDouble(), y - offsetY)
        }

        context.strokeStyle = "black"
        context.lineWidth = 0.2
        context.stroke()

        context.closePath()
    }
}


