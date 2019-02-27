package com.communistutopia.spacetrader.model

data class Ship(
    val hold: Inventory, val name: String, val fuelCapacity: Int, val hull: Int, val shields: Int,
    val hasInsurance: Boolean, val hasEscapePods: Boolean, val range: Int
)