package com.rxa392.shake;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Model M = new Model();
    private Menu mMenu;
    Switch flashControl;
    CameraManager cameraManager;
    public SensorManager sensorManager;
    Sensor accelerometer;
    public float x;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;
    boolean torch = false;
    int shakeAmmount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create the camera startup here and do rest in model

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(item.getItemId() == R.id.settings){
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        else if(item.getItemId() == R.id.share){
            switch (item.getItemId()) {
                case R.id.share:

                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                    // type of the content to be shared
                    sharingIntent.setType("text/plain");

                    // Body of the content
                    String shareBody = "https://github.com/RyanAlloway262/475app";

                    // subject of the content. you can share anything
                    String shareSubject = "CHECK OUT THIS REALLY COOL FLASHLIGHT APP";

                    // passing body of the content
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

                    // passing subject of the content
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
                    startActivity(Intent.createChooser(sharingIntent, "Share using"));
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void shakeForFlashlightButton(View view){
        flashControl = findViewById(R.id.switch12);
        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
                Toast.makeText(MainActivity.this, "This device has flash",Toast.LENGTH_SHORT).show();
                flashControl.setEnabled(true);
            }
            else Toast.makeText(MainActivity.this, "This device has no flash",Toast.LENGTH_SHORT).show();

        }
        else Toast.makeText(MainActivity.this, "This device has no camera",Toast.LENGTH_SHORT).show();
        flashControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // call the accelerometer data

//                            if(callSensor == true){
                            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                            //cameraManager.setTorchMode("0",torch);
//                            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//                            Objects.requireNonNull(sensorManager).registerListener(mSensorListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//                                    SensorManager.SENSOR_DELAY_NORMAL);
//                            mAccel = 10f;
//                            mAccelCurrent = SensorManager.GRAVITY_EARTH;
//                            mAccelLast = SensorManager.GRAVITY_EARTH;


//                            }
//                            else{}

                    }
                }
                else{
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            // disable accelerometer data
                            cameraManager.setTorchMode("0",false);
                        }
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void accelerometer(){

    }

    public void flashLight(){}

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //System.out.println("xValue: "+sensorEvent.values[0]);

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            if (mAccel > 45 && torch == false) {
                torch = true;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    try {
                        cameraManager.setTorchMode("0",torch);
                        shakeAmmount++;
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

            }

            else if (mAccel > 45 && torch == true && !(shakeAmmount > 3)){
                torch=false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    try {
                        cameraManager.setTorchMode("0",false);
                        shakeAmmount=0;
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void link(View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=L4I9nCTYUgc"));
        startActivity(browserIntent);
    }


}
