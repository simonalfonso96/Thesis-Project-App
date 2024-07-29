package com.example.test1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.checkerframework.checker.units.qual.C;

import java.io.File;
import java.io.IOException;

public class ScanActivity extends AppCompatActivity {

    ImageView imageView;
    ImageButton helpbtn, backbtn;
    Button camBtn, Uploadphotobtn;
    Bitmap bitmap;
    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        camBtn = findViewById(R.id.camBtn);
        Uploadphotobtn = findViewById(R.id.UploadphotoBtn);
        imageView = findViewById(R.id.image);
        helpbtn = findViewById(R.id.helpbtn);
        backbtn = findViewById(R.id.backbtn);

        boolean isInvalid = getIntent().getBooleanExtra("invalid", false);

        if (isInvalid) {
            // Show the invalidation dialog
            Invalidation.showInvalidation(this);
        }

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

         helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TutorialScan.class);
                startActivity(intent);
            }
        });

        camBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions((Activity) view.getContext(), new String[]{Manifest.permission.CAMERA}, 11);
            }
        });


        Uploadphotobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageSelection();

            }
        });
    }

    public void openImageSelection() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 10);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    bitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, false);
                    Intent intent = new Intent(this, Classification.class);
                    intent.putExtra("image", bitmap);
                    startActivity(intent);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11) {
            // Check if the permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with opening the camera
                Intent intent = new Intent(getApplicationContext(), Customize_camera.class);
                startActivity(intent);
            } else {
                // Permission denied, handle accordingly

            }
        }
    }
}