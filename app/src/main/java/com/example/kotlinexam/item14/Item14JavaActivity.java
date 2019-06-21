package com.example.kotlinexam.item14;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.kotlinexam.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Item14JavaActivity extends AppCompatActivity {


    private Bitmap mBitmap;

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

//        qrImageView.setOnLongClickListener(v -> {
//            PopupMenu popupMenu = new PopupMenu(this, v);
//            popupMenu.inflate(R.menu.popup);
//            popupMenu.setOnMenuItemClickListener(item -> {
//                switch (item.getItemId()) {
//                    case R.id.action_item:
//                        return true;
//                    case R.id.action_share:
//                        return true;
//                }
//                return false;
//            });
//            popupMenu.show();
//
//            return true;
//        });

        registerForContextMenu(qrImageView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_item:
                return true;
            case R.id.action_share:
                share();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void share() {
        if (mBitmap == null) {
            return;
        }

        File file = new File(getCacheDir(), "temp.jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/jpg");
            intent.putExtra(Intent.EXTRA_STREAM,
                    FileProvider.getUriForFile(this,
                            "com.example.kotlinexam.fileprovider",
                            file));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateBarcode(String content, ImageView imageView) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            mBitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 300, 300);
            Glide.with(imageView).load(mBitmap).into(imageView);
        } catch (Exception e) {

        }
    }
}
