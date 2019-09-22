package com.flogiston.test.presentation.vr.logic

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Quaternion(
    val x : Float,
    val y : Float,
    val z : Float,
    val w : Float
)
{

    fun multiply(quaternion : Quaternion) : Quaternion {
        val x = this.w * quaternion.x + quaternion.w * this.x + this.y * quaternion.z - quaternion.y * this.z
        val y = this.w * quaternion.y + quaternion.w * this.y + this.z * quaternion.x - quaternion.z * this.x
        val z = this.w * quaternion.z + quaternion.w * this.z + this.x * quaternion.y - quaternion.x * this.y
        val w = this.w * quaternion.w
        - (this.x * quaternion.x + this.y * quaternion.y + this.z * quaternion.z)
        return Quaternion(x, y, z, w)
    }

    fun conjugatedQuaternion() = Quaternion(0 - this.x, 0 - this.y, 0 - this.z, this.w)

    fun normOfQuaternion() = sqrt(w * w + x * x + y * y + z * z)

    fun reverseQuaternion() : Quaternion {
        val normOfQuaternion = normOfQuaternion()
        val normSquare = normOfQuaternion * normOfQuaternion
        val conjugatedQuaternion = conjugatedQuaternion()
        return Quaternion(
            conjugatedQuaternion.x / normSquare,
            conjugatedQuaternion.y / normSquare,
            conjugatedQuaternion.z / normSquare,
            conjugatedQuaternion.w / normSquare)
    }

    //fun normalizedQuaternion() = 0

    fun extractNormalizedVector() : Vector3 {
        val normOfQuaternion = normOfQuaternion()
        return Vector3(x / normOfQuaternion, y / normOfQuaternion, z / normOfQuaternion)
    }

    companion object {
        fun fromAngularVelocity(wx : Float, wy : Float, wz : Float, timeLapse : Long) : Quaternion {
            val vectorModule = sqrt(wx * wx + wy * wy + wz * wz)
            val sinus = sin(timeLapse * vectorModule / 2)
            return Quaternion(
                wx / vectorModule * sinus,
                wy / vectorModule * sinus,
                wz / vectorModule * sinus,
                cos(timeLapse * vectorModule / 2)
            )
        }
    }
}