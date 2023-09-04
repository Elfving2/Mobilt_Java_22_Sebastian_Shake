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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uppgift1.R;


public class Accelerometer extends Fragment {
    View view;
    TextView AccelX;
    TextView AccelY;
    TextView AccelZ;
    ImageView imageView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SensorManager sensorManager = (SensorManager) requireContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometerSensor != null) {
            sensorManager.registerListener(eventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        AccelX = view.findViewById(R.id.AccelX);
        AccelY = view.findViewById(R.id.AccelY);
        AccelZ = view.findViewById(R.id.AccelZ);
        imageView = view.findViewById(R.id.imageView);

        return view;
    }

    private SensorEventListener eventListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent event) {
            // Hantera ändringar i sensorvärden här

            int sensorType = event.sensor.getType();
            if (sensorType == Sensor.TYPE_LINEAR_ACCELERATION) {
                float accelerometerX = event.values[0];
                float accelerometerY = event.values[1];
                float accelerometerZ = event.values[2];

                AccelX.setText("X " + accelerometerX);
                AccelY.setText("Y " + accelerometerY);
                AccelZ.setText("Z " + accelerometerZ);


                if (accelerometerX > 6) {
                    Toast.makeText(getActivity(), "TO FAST", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}

