package com.github.pozo.model.ship

import com.github.pozo.network.Network

class ShipView(ship: Ship) {
    var selected: Boolean = false

    var x by kotlin.properties.Delegates.observable(ship.coordinate.x) { prop, old, new ->
        println("ship x " + prop)
        println("ship x " + old)
        println("ship x " + new)
        ship.coordinate.x = new
    }
    var y by kotlin.properties.Delegates.observable(ship.coordinate.y) { prop, old, new ->
        println("ship x " + prop)
        println("ship y " + old)
        println("ship y " + new)
        ship.coordinate.y = new
    }

    override fun toString(): String {
        return "x:" + x.toString() + "y:" + y.toString()
    }
}