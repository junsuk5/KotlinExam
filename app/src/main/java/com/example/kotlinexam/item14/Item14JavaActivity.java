package com.example.kotlinexam.item14;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.kotlinexam.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Item14JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item14_java);

        EditText editText = findViewById(R.id.editText);
        ImageView qrImageView = findViewById(R.id.qr_image);

        findViewById(R.id.generate_button).setOnClickListener(v -> {
            generateBarcode(editText.getText().toString(), qrImageView);
        });

        findViewById(R.id.scan_button).setOnClickListener(v -> {
            new IntentIntegrator(this).initiateScan();
        });
    }

    private void generateBarcode(String content, ImageView imageView) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 300, 300);
            Glide.with(imageView).load(bitmap).into(imageView);
        } catch (Exception e) {

        }
    }
}
