package com.github.pozo

import com.github.pozo.board.Canvas
import com.github.pozo.model.Coordinate
import org.w3c.dom.events.Event
import kotlin.browser.window

class MainApplication : ApplicationBase() {
    override fun dispose(): Map<String, Any> {
        return HashMap<String, Any>()

    }

    override fun start(state: Map<String, Any>) {
        val screen = Screen(Coordinate(0, 0), window.innerWidth, window.innerHeight)
        val canvas = Canvas(screen)

        canvas.drawScene()

        window.addEventListener("resize", { e: Event ->
            canvas.resizeCanvas()
            canvas.drawScene()
        }, false)
    }
}

