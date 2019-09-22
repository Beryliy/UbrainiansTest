package com.flogiston.test.presentation.vr

import androidx.lifecycle.ViewModel
import com.flogiston.test.presentation.vr.logic.Quaternion
import kotlin.math.acos
import kotlin.math.atan2

class VRViewModel : ViewModel() {
    private var lastGyroDataChange : Long? = null
    private var orientationQuaternion = Quaternion(0.0f, 0.0f, 0.0f, 1.0f)
    private var gyroscopeWorked = false


    fun gyroscopeDataChanged(gyroscopeAngularVelocities : FloatArray) {
        if (gyroscopeWorked) {
            val timeLapse = System.currentTimeMillis() - lastGyroDataChange!!
            val gyroscopeQuaternion = Quaternion.fromAngularVelocity(
                gyroscopeAngularVelocities[X],
                gyroscopeAngularVelocities[Y],
                gyroscopeAngularVelocities[Z],
                timeLapse
            )
            orientationQuaternion = orientationQuaternion.multiply(gyroscopeQuaternion)
        } else {
            lastGyroDataChange = System.currentTimeMillis()
            gyroscopeWorked = true
        }
    }

    fun accelerometerDataChanged(accelerometerAngularAccelerations : FloatArray) {
        if(gyroscopeWorked){
            val accelerometerQuaternion = Quaternion(
                accelerometerAngularAccelerations[X],
                accelerometerAngularAccelerations[Y],
                accelerometerAngularAccelerations[Z],
                0.0f
            )
            val orientationQuaternionTurnedByAccelerometer = orientationQuaternion.multiply(accelerometerQuaternion)
                .multiply(orientationQuaternion.reverseQuaternion())
            val quaternionNormalizedVector = orientationQuaternionTurnedByAccelerometer.extractNormalizedVector()
            val rotationAxisVector = quaternionNormalizedVector.countTiltVector()
            val rotationAngle = acos(rotationAxisVector.y)
            orientationQuaternion = rotationAxisVector.buildQuaternion(rotationAngle, antiAliasingKoef)
                .multiply(orientationQuaternion)
        }
    }

    fun magnetometerDataChanged(vectorOfMagneticInduction : FloatArray) {
        if(gyroscopeWorked){
            val magnetometerQuaternion = Quaternion(
                vectorOfMagneticInduction[X],
                vectorOfMagneticInduction[Y],
                vectorOfMagneticInduction[Z],
                0.0f
            )
            val orientationQuaternionTurnedByMagnetometer = orientationQuaternion.multiply(magnetometerQuaternion)
                .multiply(magnetometerQuaternion.reverseQuaternion())
            val rotationAngle = atan2(
                orientationQuaternionTurnedByMagnetometer.z.toDouble(),
                orientationQuaternionTurnedByMagnetometer.x.toDouble()
            ).toFloat()
            orientationQuaternion = Quaternion(0.0f, 1.0f , 0.0f, rotationAngle * antiAliasingKoef)
                .multiply(orientationQuaternion)
        }
    }

    companion object {
        const val X = 0
        const val Y = 1
        const val Z = 2
        val antiAliasingKoef = 0.1f
    }
}