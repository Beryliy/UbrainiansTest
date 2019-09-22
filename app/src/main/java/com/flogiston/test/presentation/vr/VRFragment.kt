package com.flogiston.test.presentation.vr


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.flogiston.test.R

/**
 * A simple [Fragment] subclass.
 */
class VRFragment : Fragment() {
    private lateinit var sensorManager : SensorManager
    private var gyroscope : Sensor? = null
    private var accelerometer : Sensor? = null
    private var magnetometer : Sensor? = null
    private var gyroscopeQuaternion = FloatArray(4)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vr, container, false)
    }

    override fun onResume() {
        super.onResume()
        gyroscope?.also { gyroscope ->
            sensorManager.registerListener(object : SensorEventListener {
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

                }

                override fun onSensorChanged(event: SensorEvent?) {

                }
            },
                gyroscope,
                SensorManager.SENSOR_DELAY_NORMAL)
        }

        accelerometer?.also { accelerometer ->
            sensorManager.registerListener(object : SensorEventListener {
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

                }

                override fun onSensorChanged(event: SensorEvent?) {

                }
            },
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL)
        }

        magnetometer?.also { magnetometer ->
            sensorManager.registerListener(object : SensorEventListener {
                override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

                }

                override fun onSensorChanged(event: SensorEvent?) {

                }
            },
                magnetometer,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
}
