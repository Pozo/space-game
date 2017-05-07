package com.github.pozo.paint

import org.w3c.dom.HTMLImageElement
import kotlin.browser.window

interface Painter {

    fun getImage(path: String): HTMLImageElement {
        val image = window.document.createElement("img") as HTMLImageElement
        image.src = path
        return image
    }
}

