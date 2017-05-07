package com.github.pozo

abstract class ApplicationBase {
    abstract fun start(state: Map<String, Any>)
    abstract fun dispose(): Map<String, Any>
}