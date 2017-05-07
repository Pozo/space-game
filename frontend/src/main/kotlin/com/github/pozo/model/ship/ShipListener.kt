package com.github.pozo.model.ship

import com.github.pozo.model.Coordinate

interface ShipListener {
    fun select()
    fun moveTo(coordinate: Coordinate)
}