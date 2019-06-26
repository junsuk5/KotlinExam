package com.example.kotlinexam.item17java;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.kotlinexam.R;

import java.util.Arrays;
import java.util.List;

public class Item17javaActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSIONS = 10;

    private List<String> REQUIRED_PERMISSIONS
            = Arrays.asList(Manifest.permission.CAMERA);

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                // 허락 됨
                Toast.makeText(this, "허락 됨", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this,
                        "Permissions not granted by the user.",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(
                    getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item17java);

        if (allPermissionsGranted()) {
            // 허락 됨
            Toast.makeText(this, "허락 됨", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(
                    this, (String[]) REQUIRED_PERMISSIONS.toArray(), REQUEST_CODE_PERMISSIONS);
        }
    }
}
