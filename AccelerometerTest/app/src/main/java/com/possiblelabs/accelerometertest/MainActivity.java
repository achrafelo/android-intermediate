package com.possiblelabs.accelerometertest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by possiblelabs on 7/20/15.
 */
public class MainActivity extends Activity implements SensorEventListener {


    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor light;
    private TextView x, y, z, l;
    private long lastTime;
    private static final int MAX_TIME_REFRESH = 1000;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main_activity);
        x = (TextView) findViewById(R.id.txtX);
        y = (TextView) findViewById(R.id.txtY);
        z = (TextView) findViewById(R.id.txtZ);
        l = (TextView) findViewById(R.id.txtL);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (null == accelerometer)
            finish();

        if (null == light)
            finish();


        lastTime = System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            long now = System.currentTimeMillis();

            if (now - lastTime > MAX_TIME_REFRESH) {
                lastTime = now;

                float rawX = sensorEvent.values[0];
                float rawY = sensorEvent.values[1];
                float rawZ = sensorEvent.values[2];

                x.setText(String.valueOf(rawX));
                y.setText(String.valueOf(rawY));
                z.setText(String.valueOf(rawZ));
            }

        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            l.setText(sensorEvent.values[0] + "");
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
