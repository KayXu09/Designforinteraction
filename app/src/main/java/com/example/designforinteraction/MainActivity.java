package com.example.designforinteraction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends Activity {
    private SensorManager sensorManager;
    private int i = 0;
    private static ArrayList<String> resList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvSYG= findViewById(R.id.tvSYG);
        final TextView tvList = findViewById(R.id.tvList);

        tvSYG.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent menuIntent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(menuIntent);
                    }
                });

        if (getIntent().hasExtra("total_points")) {
            Bundle bundle = getIntent().getExtras();
            String points_i = bundle.getString("total_points");
            resList.add(points_i);

            String resStr = "";
            int tot = 0;
            i = 1;
            for (String pts : resList) {
                resStr += "Round " + i++ + ": " + pts + "\n";
                tot += Integer.parseInt(pts);
            }

            resStr += "Total: " + tot;


            tvList.setText(resStr);
        }

    }


}







