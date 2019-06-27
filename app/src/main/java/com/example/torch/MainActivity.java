package com.example.torch;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.toggleButton);
        boolean feature_carera_flash = getPackageManager().hasSystemFeature(getPackageManager().FEATURE_CAMERA_FLASH);

        final boolean camera_light = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 60);

        final CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    String cameraID = cameraManager.getCameraIdList()[0];
                    if (btn.isChecked())
                    {
                        cameraManager.setTorchMode(cameraID, true);
                    }
                    else
                    {
                        cameraManager.setTorchMode(cameraID, false);
                    }

                }
                catch (CameraAccessException e)
                {
                    e.printStackTrace();
                }
            }
        });



    }
}
