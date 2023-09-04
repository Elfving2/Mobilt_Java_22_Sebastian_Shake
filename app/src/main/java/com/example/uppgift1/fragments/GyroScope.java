package com.example.uppgift1.fragments;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uppgift1.R;

public class GyroScope extends Fragment implements SensorEventListener  {
    View view;
    public View imageView;
    public TextView gyroX;
    public TextView gyroY;
    public TextView gyroZ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SensorManager sensorManager = (SensorManager) requireContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor gyroScopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (gyroScopeSensor != null ) {
            sensorManager.registerListener(eventListener, gyroScopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    private SensorEventListener eventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Hantera ändringar i sensorvärden här

            int sensorType = event.sensor.getType();

            if (sensorType == Sensor.TYPE_GYROSCOPE) {
                float gyroscopeX = event.values[0];
                float gyroscopeY = event.values[1];
                float gyroscopeZ = event.values[2];

                float rotation = (gyroscopeX * 360) - 180;

                imageView.setRotation(rotation);

                // set values into textViews
                gyroX.setText("X " + gyroscopeX);
                gyroY.setText("Y " + gyroscopeY);
                gyroZ.setText("Z " +gyroscopeZ);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_new, container, false);

        gyroX = view.findViewById(R.id.gyroX);
        gyroY = view.findViewById(R.id.gyroY);
        gyroZ = view.findViewById(R.id.gyroZ);
        imageView = view.findViewById(R.id.imageView);

        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}