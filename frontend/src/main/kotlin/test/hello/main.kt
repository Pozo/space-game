package test.hello

import kotlin.browser.document

fun main(args: Array<String>) {

    if (document.body != null) {
        start()
    } else {
        document.addEventListener("DOMContentLoaded", { start() })
    }
}

fun start() {
    Canvas().connectAndDraw()
}

