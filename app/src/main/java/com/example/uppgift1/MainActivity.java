package com.example.uppgift1;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.uppgift1.fragments.Accelerometer;
import com.example.uppgift1.fragments.GyroScope;
import com.example.uppgift1.fragments.Temperature;

public class MainActivity extends AppCompatActivity {
    Button fragmentOne, fragmentTwo, fragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentOne = findViewById(R.id.fragmentOne);
        fragmentTwo = findViewById(R.id.fragmentTwo);
        fragmentThree = findViewById(R.id.fragmentThree);

        fragmentOne.setOnClickListener(view ->
                replaceFragment(new GyroScope()));


        fragmentTwo.setOnClickListener(view ->
                replaceFragment(new Temperature()));

        fragmentThree.setOnClickListener(view ->
                replaceFragment(new Accelerometer()));
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }
}