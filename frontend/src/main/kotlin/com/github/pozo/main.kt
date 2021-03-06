package com.github.pozo

import kotlin.browser.document
import kotlin.dom.hasClass

fun main(args: Array<String>) {
    var application: ApplicationBase? = null

    val state: dynamic = module.hot?.let { hot ->
        hot.accept()

        hot.dispose { data ->
            data.appState = application?.dispose()
            application = null
        }

        hot.data
    }

    if (document.body != null) {
        application = start(state)
    } else {
        application = null
        document.addEventListener("DOMContentLoaded", { application = start(state) })
    }
}

fun start(state: dynamic): ApplicationBase? {
    if (document.body?.hasClass("kotlin-app") ?: false) {
        val application = MainApplication()

        @Suppress("UnsafeCastFromDynamic")
        application.start(state?.appState ?: emptyMap())

        return application
    } else {
        return null
    }
}