package com.github.pozo.model.ship

import com.github.pozo.model.Coordinate
import com.github.pozo.model.Model

class ShipPresenter(val ship: Ship, val shipView: ShipView) : ShipListener {
    override fun moveTo(coordinate: Coordinate) {
        shipView.x = coordinate.x
        shipView.y = coordinate.y
        println(shipView.toString())
        Model.network.send(JSON.stringify(ship))

    }

    override fun select() {
        shipView.selected = !shipView.selected
    }

}