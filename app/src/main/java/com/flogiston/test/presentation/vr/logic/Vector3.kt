package com.flogiston.test.presentation.vr.logic

import kotlin.math.sqrt

class Vector3(
    private val x : Float,
    val y : Float,
    private val z : Float
) {
    fun countTiltVector() : Vector3 {
        val denominator = sqrt(x * x + z * z)
        return Vector3(0 - z / denominator, 0.0f, y / denominator)
    }

    fun buildQuaternion(angle : Float, antiAliasKoef : Float) = Quaternion(x, y, z, angle * antiAliasKoef)
}