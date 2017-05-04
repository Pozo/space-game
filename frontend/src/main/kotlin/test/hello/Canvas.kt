package test.hello

import org.w3c.dom.MessageEvent
import org.w3c.dom.WebSocket
import org.w3c.dom.events.Event


class Canvas {
    val canvas = kotlin.browser.document.getElementById("canvas") as org.w3c.dom.HTMLCanvasElement
    val renderingContext2D = canvas.getContext("2d")!! as org.w3c.dom.CanvasRenderingContext2D

    var line = 0

    init {
        canvas.width = kotlin.browser.window.innerWidth
        canvas.height = kotlin.browser.window.innerHeight
        renderingContext2D.translate((canvas.width / 2).toDouble(), (canvas.height / 2).toDouble())
    }

    fun connectAndDraw() {


        val socket = WebSocket("ws://localhost.com:8025/")
        socket.onmessage = { onMessage(socket, it) }
    }

    fun onMessage(ws: WebSocket, event: Event) {
        if (event is MessageEvent) {
            val data = event.data

            if (data is String) {
                renderingContext2D.font = "12px sans-serif"
                renderingContext2D.fillText(data, 0.0, (renderingContext2D.font.size * line++).toDouble())
            }
        }
    }
}


