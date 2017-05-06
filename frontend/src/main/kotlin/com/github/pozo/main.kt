package com.github.pozo

import org.w3c.dom.events.Event
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {

    if (document.body != null) {
        draw()
    } else {
        document.addEventListener("DOMContentLoaded", {
            draw()
        })

    }
}

private fun draw() {
    val playerProperties = Screen(Coordinate(0, 0), window.innerWidth, window.innerHeight)
    val canvas = Canvas(playerProperties)

    canvas.connectAndDraw()

    window.addEventListener("resize", { e: Event ->
        canvas.resizeCanvas()
    }, false)
}
