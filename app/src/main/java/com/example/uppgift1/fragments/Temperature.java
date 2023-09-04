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

import com.example.uppgift1.R;
public class Temperature extends Fragment {
    View view;
    TextView temp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SensorManager sensorManager = (SensorManager) requireContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (tempSensor != null ) {
            sensorManager.registerListener(eventListener, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    private SensorEventListener eventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Hantera ändringar i sensorvärden här

            int sensorType = event.sensor.getType();

            if (sensorType == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                float temperatureInCelsius = event.values[0];
                temp.setText(temperatureInCelsius + "°C");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_temperature, container, false);
        temp = view.findViewById(R.id.temprature);
        return view;
    }
}