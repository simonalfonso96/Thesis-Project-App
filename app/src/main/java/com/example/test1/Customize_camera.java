package com.example.test1;

import static java.io.File.createTempFile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.logging.Handler;

public class Customize_camera extends AppCompatActivity implements ImageAnalysis.Analyzer, View.OnClickListener {
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    PreviewView previewView;
    private ImageButton bCapture;

    private ImageView Backpress;
    private ImageCapture imageCapture;
    private String currentPhotoPath;
    TextView guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_camera);

        previewView = findViewById(R.id.Preview);
        bCapture = findViewById(R.id.TakeApic);
        guide = findViewById(R.id.guide);
        Backpress = findViewById(R.id.Backpress);

        guide.setText("Align the frame of the dog's face to enable easy classification");

        Backpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        bCapture.setOnClickListener(this);

        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                startCameraX(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, getExecutor());



    }
    Executor getExecutor() {
        return ContextCompat.getMainExecutor(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.TakeApic:
                capturePhoto();
                break;
        }
    }
    @SuppressLint("RestrictedApi")
    private void startCameraX(ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        Preview preview = new Preview.Builder()
                .build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        // Image capture use case
        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .setTargetResolution(new Size(1920, 1080))
                .build();

        // Image analysis use case
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build();

        imageAnalysis.setAnalyzer(getExecutor(), this);
        //bind to lifecycle:
        cameraProvider.bindToLifecycle((LifecycleOwner) this, cameraSelector, preview, imageCapture);
    }
    @Override
    public void analyze(@NonNull ImageProxy image) {
        // image processing here for the current frame
        Log.d("TAG", "analyze: got the frame at: " + image.getImageInfo().getTimestamp());
        image.close();
    }
    private void capturePhoto() {

        bCapture.setEnabled(false);
        previewView.setVisibility(View.GONE);
        new Thread(() -> {
            String filename = "photo";
            File storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File imageFile = createTempFile(filename, ".jpg", storageDirectory);
                currentPhotoPath = imageFile.getAbsolutePath();
                imageCapture.takePicture(new ImageCapture.OutputFileOptions.Builder(imageFile).build(), getExecutor(),
                        new ImageCapture.OnImageSavedCallback() {
                            @Override
                            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                                // Image capture is successful, launch the new activity to display the image
                                Uri imageUri = Uri.fromFile(imageFile);
                                launchDisplayActivity(imageUri);
                                bCapture.setEnabled(true);
                            }
                            @Override
                            public void onError(@NonNull ImageCaptureException exception) {
                                // Handle error when capturing the image
                                exception.printStackTrace();
                                bCapture.setEnabled(true);
                            }
                        });

            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
                // Handle the unsupported operation gracefully
                Toast.makeText(Customize_camera.this, "Cannot insert file", Toast.LENGTH_SHORT).show();
            }
            runOnUiThread(() -> {
                previewView.setVisibility(View.VISIBLE); // Make the preview visible again
                guide.setText("Hold still...");
            });
        }).start();
    }
    private void launchDisplayActivity(Uri imageUri) {
        try {
            // Get the bitmap from the captured image
            Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);

            // Rotate the bitmap based on the image's orientation
            ExifInterface exif = new ExifInterface(currentPhotoPath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            bitmap = rotateBitmap(bitmap, orientation);

            // Crop the bitmap to the dimensions of the frame with ID 'center'
            View frame = findViewById(R.id.center_cam);
            int targetWidth = frame.getWidth();
            int targetHeight = frame.getHeight();
            bitmap = cropBitmap(bitmap, targetWidth, targetHeight);

            // Save the cropped bitmap to a new file
            File croppedImageFile = createTempFile("cropped", ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            currentPhotoPath = croppedImageFile.getAbsolutePath();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(croppedImageFile));

            Intent intent = new Intent(this, Classification.class);
            intent.putExtra("imageUri", Uri.fromFile(croppedImageFile).toString());
//            intent.putExtra("closeActivity", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                break;
            default:
                return bitmap;
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

//    private Bitmap cropBitmap(Bitmap bitmap, int targetWidth, int targetHeight) {
//        int width = bitmap.getWidth();
//        int height = bitmap.getHeight();
//        int x = (width - targetWidth) / 2;
//        int y = (height - targetHeight) / 2;
//        return Bitmap.createBitmap(bitmap, x, y, targetWidth, targetHeight);
//    }
    private Bitmap cropBitmap(Bitmap bitmap, int targetWidth, int targetHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // Ensure x and y are within the bounds of the original bitmap
        int x = Math.max(0, (width - targetWidth) / 2);
        int y = Math.max(0, (height - targetHeight) / 2);

        // Ensure targetWidth and targetHeight are within the bounds of the original bitmap
        targetWidth = Math.min(targetWidth, width - x);
        targetHeight = Math.min(targetHeight, height - y);

        return Bitmap.createBitmap(bitmap, x, y, targetWidth, targetHeight);
    }
}