package com.example.designforinteraction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class Main2Activity extends Activity {

    private SensorManager sensorManager;
    private double points = 0;
    private double last_x = 0;


    private double last_y = 0;
    private boolean start = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // use x and y
        Sensor sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE); // use z
        final TextView score = findViewById(R.id.Score);


        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                double x = Math.abs(event.values[0]);
                double y = Math.abs(event.values[1]);
                if (!start) {
                    last_x = x;
                    last_y = y;
                    start = true;
                }
                else {
                    points += Math.sqrt((last_x - x) * (last_x - x) + (last_y - y) * (last_y - y));
                    last_x = x;
                    last_y = y;
                }
                int points_i = (int)(points/100.);
                score.setText("" + points_i);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

        }, sensor, SensorManager.SENSOR_DELAY_FASTEST);

        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                double z = event.values[2];
                //points += Math.abs(z);
                //int points_i = (int)(points/10.);
                //score.setText("" + points_i);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

        }, sensor2, SensorManager.SENSOR_DELAY_FASTEST);

        final TextView done = findViewById(R.id.Done);

        done.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                        int points_i = (int)(points/100.);
                        String message = "" + points_i;
                        intent.putExtra("total_points", message);
                        startActivity(intent);
                    }
                });

    }
}

