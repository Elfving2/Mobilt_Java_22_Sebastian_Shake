package com.example.uppgift1;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewFragment extends Fragment implements SensorEventListener  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public View imageView;
    public TextView xView;
    public TextView yView;
    public TextView zView;
    public TextView gyroX;
    public TextView gyroY;
    public TextView gyroZ;

    TextView tempView;



    public NewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment newFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewFragment newInstance(String param1, String param2) {
        NewFragment fragment = new NewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        SensorManager sensorManager = (SensorManager) requireContext().getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor gyroScopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Sensor tempSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);

        if (tempSensor != null || accelerometerSensor != null || gyroScopeSensor != null ) {
            sensorManager.registerListener(eventListener, tempSensor, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(eventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(eventListener, gyroScopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    private SensorEventListener eventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            // Hantera ändringar i sensorvärden här
            int sensorType = event.sensor.getType();


            if(xView != null && yView != null && zView != null) {

                if (sensorType == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                    float temperatureInCelsius = event.values[0];
                    tempView.setText(String.valueOf(temperatureInCelsius));
                }
                if (sensorType == Sensor.TYPE_ACCELEROMETER) {
                    float accelerometerX = event.values[0];
                    float accelerometerY = event.values[1];
                    float accelerometerZ = event.values[2];

                    float rotation = (accelerometerX * 360) - 180;

                    imageView.setRotation(rotation);

                    xView.setText(String.valueOf(accelerometerX));
                    yView.setText(String.valueOf(accelerometerY));
                    zView.setText(String.valueOf(accelerometerZ));


                    if (accelerometerX > 6) {
                        Toast.makeText(getActivity(), "TO FAST", Toast.LENGTH_LONG).show();
                    }

                }  else if (sensorType == Sensor.TYPE_GYROSCOPE) {
                    float gyroscopeX = event.values[0];
                    float gyroscopeY = event.values[1];
                    float gyroscopeZ = event.values[2];

                    // set values into textViews
                    gyroX.setText(String.valueOf(gyroscopeX));
                    gyroY.setText(String.valueOf(gyroscopeY));
                    gyroZ.setText(String.valueOf(gyroscopeZ));
                }
            } else {
                // if they are null set all get all textViews
                xView = getView().findViewById(R.id.accelerometerX);
                yView = getView().findViewById(R.id.accelerometerY);
                zView = getView().findViewById(R.id.accelerometerZ);

                gyroX = getView().findViewById(R.id.gyroX);
                gyroY = getView().findViewById(R.id.gyroY);
                gyroZ = getView().findViewById(R.id.gyroZ);

                imageView = getView().findViewById(R.id.imageView);

                tempView = getView().findViewById(R.id.temprature);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}