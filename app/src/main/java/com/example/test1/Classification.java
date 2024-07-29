package com.example.test1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;


import android.Manifest;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test1.Adapter.Information;
import com.example.test1.Adapter.InformationAdapter;
import com.example.test1.Adapter.SliderAdapter;
import com.example.test1.Domain.SliderItem;
import com.example.test1.ml.Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;


public class Classification extends AppCompatActivity {

    ViewPager2 viewpager2;
    TextView classified;
    ImageView imageView;
    FloatingActionButton recamBtn, charBtn, reUploadPhoto;
    RatingBar ratingBar1, ratingBar2, ratingBar3, ratingBar4, ratingBar5, ratingBar6, ratingBar7;
    RecyclerView recyclerView;
    private LinearLayout linear_expand;
    private CoordinatorLayout Cardview;
    ImageButton backbtn;
    Bitmap bitmap;
    private ProgressBar progressB;
    private TextView progress_text;
    int imageSize = 224;
    private String currentPhotoPath;

    ImageButton arrowUp, arrowDown;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);

        viewpager2 = findViewById(R.id.viewpager2);
        recyclerView = findViewById(R.id.recyclerView);
        ratingBar1 = findViewById(R.id.ratingbar1);
        ratingBar2 = findViewById(R.id.ratingbar2);
        ratingBar3 = findViewById(R.id.ratingbar3);
        ratingBar4 = findViewById(R.id.ratingbar4);
        ratingBar5 = findViewById(R.id.ratingbar5);
        ratingBar6 = findViewById(R.id.ratingbar6);
        ratingBar7 = findViewById(R.id.ratingbar7);
        imageView = findViewById(R.id.imageView);
        classified = findViewById(R.id.classified);
        progressB = findViewById(R.id.progressB);
        progress_text = findViewById(R.id.progress_text);
        charBtn = findViewById(R.id.chartBtn);
        backbtn = findViewById(R.id.backbtn);
        recamBtn = findViewById(R.id.recamBtn);
        reUploadPhoto = findViewById(R.id.reUploadPhotoBtn);

        Cardview = findViewById(R.id.cardview);
        linear_expand = findViewById(R.id.linear_expand);
        arrowUp = findViewById(R.id.arrow_Up);
        arrowDown = findViewById(R.id.arrow_Down);


        String imageUriString = getIntent().getStringExtra("imageUri");
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            Bitmap imageBitmap ;
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                int dimension = Math.min(imageBitmap.getWidth(), imageBitmap.getHeight());
                imageBitmap = ThumbnailUtils.extractThumbnail(imageBitmap, dimension, dimension);
                imageBitmap = Bitmap.createScaledBitmap(imageBitmap, imageSize, imageSize, false);
                imageView.setImageBitmap(imageBitmap);
                classifyImage(imageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle any exceptions that may occur while processing the image.
            }
        }
        Bitmap imageBitmap = getIntent().getParcelableExtra("image");
        if(imageBitmap != null){
            imageView.setImageBitmap(imageBitmap);
            classifyImage(imageBitmap);

        }

        Cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linear_expand.getVisibility() == View.GONE) {
                    linear_expand.setVisibility(View.VISIBLE);
                    arrowUp.setVisibility(View.VISIBLE);
                    arrowDown.setVisibility(View.GONE);
                } else {
                    linear_expand.setVisibility(View.GONE);
                    arrowUp.setVisibility(View.GONE);
                    arrowDown.setVisibility(View.VISIBLE);
                }
            }
        });
        arrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linear_expand.getVisibility() == View.GONE) {
                    linear_expand.setVisibility(View.VISIBLE);
                    arrowUp.setVisibility(View.VISIBLE);
                    arrowDown.setVisibility(View.GONE);
                } else {
                    linear_expand.setVisibility(View.GONE);
                    arrowUp.setVisibility(View.GONE);
                    arrowDown.setVisibility(View.VISIBLE);
                }
            }
        });
        arrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (linear_expand.getVisibility() == View.GONE) {
                    linear_expand.setVisibility(View.VISIBLE);
                    arrowUp.setVisibility(View.VISIBLE);
                    arrowDown.setVisibility(View.GONE);
                } else {
                    linear_expand.setVisibility(View.GONE);
                    arrowUp.setVisibility(View.GONE);
                    arrowDown.setVisibility(View.VISIBLE);
                }
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recamBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Customize_camera.class);
                startActivity(intent);
            }
        });
        reUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageSelection();
            }
        });
    }

    //UploadPhoto
    public void openImageSelection() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    public void classifyImage(Bitmap image) {
        try {

            Model model = Model.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());
            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f)); // Red channel
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));  // Green channel
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));         // Blue channel
                }
            }
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            Model.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            String[] labels = new String[20];
            int cnt = 0;
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("labels.txt")));
                String line = bufferedReader.readLine();
                while (line != null) {
                    labels[cnt] = line;
                    cnt++;
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                    e.printStackTrace();
            }

            float[] confidences = outputFeature0.getFloatArray();
            int maxPos = 0;
            float maxConfidences = 0;
               for (int i = 0; i < confidences.length; i++) {
                   if (confidences[i] > maxConfidences) {
                       maxConfidences = confidences[i];
                       maxPos = i;

                       classified.setText(labels[maxPos]);
                   }
               }



            float confidenceThreshold = 0.799f; // You can adjust this value as needed
            if (maxConfidences >= confidenceThreshold) {

            } else {
                    Intent closeIntent = new Intent(this, ScanActivity.class);
                    closeIntent.putExtra("invalid", true);
                    closeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(closeIntent);
                    finish();
            }

               double progress = 0;
               while (progress <= maxPos) {
                   progress = (confidences[getMax(outputFeature0.getFloatArray())] * 100);
                    double finalProgress = Double.parseDouble(String.valueOf(progress));
                    ValueAnimator animator = ValueAnimator.ofFloat(0, (float) finalProgress);
                    animator.setDuration(1000);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            float animatedValue = (float) valueAnimator.getAnimatedValue();
                            progressB.setProgress((int) animatedValue);
                            String progressString = String.format("%.1f", animatedValue);
                            progress_text.setText(progressString + "%");
                        }
                    });
                    animator.start();
                }

            recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Add this line
            List<Information> informationList = new ArrayList<>();

            InformationAdapter informationAdapter = new InformationAdapter(informationList);
            recyclerView.setAdapter(informationAdapter);
            recyclerView.setNestedScrollingEnabled(false);

            List<SliderItem> sliderItems = new ArrayList<>();
            switch (maxPos) {


                //Airedale Terrier
                case 0:
                    sliderItems.add(new SliderItem(R.drawable.at1));
                    sliderItems.add(new SliderItem(R.drawable.at2));
                    sliderItems.add(new SliderItem(R.drawable.at3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.at1),true));
                    informationList.add(new Information("Description", getString(R.string.at2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.at4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.at3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.at5),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;

                //American Bully
                case 1:
                    sliderItems.add(new SliderItem(R.drawable.ab1));
                    sliderItems.add(new SliderItem(R.drawable.ab2));
                    sliderItems.add(new SliderItem(R.drawable.ab3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About1),true));
                    informationList.add(new Information("Description", getString(R.string.Traits1),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food1),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note1),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health1),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(1);
                    ratingBar3.setRating(2);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(2);
                    break;

                //Australian Shepherd
                case 2:
                    sliderItems.add(new SliderItem(R.drawable.as1));
                    sliderItems.add(new SliderItem(R.drawable.as2));
                    sliderItems.add(new SliderItem(R.drawable.as3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.as1),true));
                    informationList.add(new Information("Description", getString(R.string.as2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.as4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.as3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.as5),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(4);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(4);
                    break;

                // Basset
                case 3:
                    sliderItems.add(new SliderItem(R.drawable.bh1));
                    sliderItems.add(new SliderItem(R.drawable.bh2));
                    sliderItems.add(new SliderItem(R.drawable.bh3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.bh1),true));
                    informationList.add(new Information("Description", getString(R.string.bh2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.bh4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.bh3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.bh5),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;

                // Beagle
                case 4:
                    sliderItems.add(new SliderItem(R.drawable.beagle1));
                    sliderItems.add(new SliderItem(R.drawable.beagle2));
                    sliderItems.add(new SliderItem(R.drawable.beagle3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About2),true));
                    informationList.add(new Information("Description", getString(R.string.Traits2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food2),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note2),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health2),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;

                // Bichon
                case 5:
                    sliderItems.add(new SliderItem(R.drawable.bf1));
                    sliderItems.add(new SliderItem(R.drawable.bf2));
                    sliderItems.add(new SliderItem(R.drawable.bf3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.bf1),true));
                    informationList.add(new Information("Description", getString(R.string.bf2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.bf4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.bf3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.bf5),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(2);
                    ratingBar3.setRating(2);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(2);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(2);
                    break;

                //Chihuahua
                case 6:
                    sliderItems.add(new SliderItem(R.drawable.chi1));
                    sliderItems.add(new SliderItem(R.drawable.chi2));
                    sliderItems.add(new SliderItem(R.drawable.chi3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About4),true));
                    informationList.add(new Information("Description", getString(R.string.Traits4),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note4),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health4),false));
                    ratingBar1.setRating(4);
                    ratingBar2.setRating(5);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(3);
                    break;

                // Chow chow
                case 7:
                    sliderItems.add(new SliderItem(R.drawable.chow1));
                    sliderItems.add(new SliderItem(R.drawable.chow2));
                    sliderItems.add(new SliderItem(R.drawable.chow3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About5),true));
                    informationList.add(new Information("Description", getString(R.string.Traits5),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food5),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note5),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health5),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(1);
                    ratingBar3.setRating(3);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(2);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(4);
                    break;

                //Corgi
                case 8:
                    sliderItems.add(new SliderItem(R.drawable.cor1));
                    sliderItems.add(new SliderItem(R.drawable.cor2));
                    sliderItems.add(new SliderItem(R.drawable.cor3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About6),true));
                    informationList.add(new Information("Description", getString(R.string.Traits6),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food6),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note6),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health6),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(4);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(4);
                    break;

                //Dalmatian
                case 9:
                    sliderItems.add(new SliderItem(R.drawable.dal1));
                    sliderItems.add(new SliderItem(R.drawable.dal2));
                    sliderItems.add(new SliderItem(R.drawable.dal3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About8),true));
                    informationList.add(new Information("Description", getString(R.string.Traits8),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food8),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note8),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health8),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;

                // Doberman
                case 10:
                    sliderItems.add(new SliderItem(R.drawable.dp1));
                    sliderItems.add(new SliderItem(R.drawable.dp2));
                    sliderItems.add(new SliderItem(R.drawable.dp3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About9),true));
                    informationList.add(new Information("Description", getString(R.string.Traits9),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food9),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note9),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health9),false));
                    ratingBar1.setRating(5);
                    ratingBar2.setRating(3);
                    ratingBar3.setRating(5);
                    ratingBar4.setRating(5);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(2);
                    break;

                //German Shepperd
                case 11:
                    sliderItems.add(new SliderItem(R.drawable.gs1));
                    sliderItems.add(new SliderItem(R.drawable.gs2));
                    sliderItems.add(new SliderItem(R.drawable.gs3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About10),true));
                    informationList.add(new Information("Description", getString(R.string.Traits10),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food10),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note10),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health10),false));
                    ratingBar1.setRating(4);
                    ratingBar2.setRating(3);
                    ratingBar3.setRating(5);
                    ratingBar4.setRating(5);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(2);
                    break;

                //Golden Retriever
                case 12:
                    sliderItems.add(new SliderItem(R.drawable.gr1));
                    sliderItems.add(new SliderItem(R.drawable.gr2));
                    sliderItems.add(new SliderItem(R.drawable.gr3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About11),true));
                    informationList.add(new Information("Description", getString(R.string.Traits11),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food11),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note11),false));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food11),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health11),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(1);
                    ratingBar3.setRating(3);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(2);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(4);
                    break;

                // Jack russell
                case 13:
                    sliderItems.add(new SliderItem(R.drawable.jr1));
                    sliderItems.add(new SliderItem(R.drawable.jr2));
                    sliderItems.add(new SliderItem(R.drawable.jr3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.jr1),true));
                    informationList.add(new Information("Description", getString(R.string.jr2),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.jr4),false));
                    informationList.add(new Information("Did you know?", getString(R.string.jr3),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.jr5),false));
                    ratingBar1.setRating(3);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(3);
                    ratingBar7.setRating(2);
                    break;


                //Pomeranian
                case 14:
                    sliderItems.add(new SliderItem(R.drawable.pom1));
                    sliderItems.add(new SliderItem(R.drawable.pom2));
                    sliderItems.add(new SliderItem(R.drawable.pom3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About12),true));
                    informationList.add(new Information("Description", getString(R.string.Traits12),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food12),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note12),false));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food12),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health12),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(5);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(3);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(3);
                    break;

                //pug
                case 15:
                    sliderItems.add(new SliderItem(R.drawable.pug1));
                    sliderItems.add(new SliderItem(R.drawable.pug2));
                    sliderItems.add(new SliderItem(R.drawable.pug3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About14),true));
                    informationList.add(new Information("Description", getString(R.string.Traits14),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food14),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note14),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health14),false));
                    ratingBar1.setRating(1);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(4);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(3);
                    break;

                //Shiba Inu
                case 16:
                    sliderItems.add(new SliderItem(R.drawable.si1));
                    sliderItems.add(new SliderItem(R.drawable.si2));
                    sliderItems.add(new SliderItem(R.drawable.si3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About15),true));
                    informationList.add(new Information("Description", getString(R.string.Traits15),true));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food15),false));
                    informationList.add(new Information("Did you know?", getString(R.string.Note15),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.Health15),false));
                    ratingBar1.setRating(2);
                    ratingBar2.setRating(4);
                    ratingBar3.setRating(4);
                    ratingBar4.setRating(4);
                    ratingBar5.setRating(4);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(4);
                    break;

                // Shih tzu
                case 17:
                    sliderItems.add(new SliderItem(R.drawable.st1));
                    sliderItems.add(new SliderItem(R.drawable.st2));
                    sliderItems.add(new SliderItem(R.drawable.st3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.About16),true));
                    informationList.add(new Information("Description", getString(R.string.Traits16),true));
                    informationList.add(new Information("Did you know?", getString(R.string.Note16),false));
                    informationList.add(new Information("Appropriate Food", getString(R.string.Food16),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.health16),false));
                    ratingBar1.setRating(1);
                    ratingBar2.setRating(2);
                    ratingBar3.setRating(2);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(2);
                    ratingBar6.setRating(2);
                    ratingBar7.setRating(2);
                    break;

                //Siberian Husky
                case 18:
                    sliderItems.add(new SliderItem(R.drawable.husky1));
                    sliderItems.add(new SliderItem(R.drawable.husky2));
                    sliderItems.add(new SliderItem(R.drawable.husky3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.huskyAbout),true));
                    informationList.add(new Information("Description", getString(R.string.huskytraits),true));
                    informationList.add(new Information("Appropriate Food",getString(R.string.huskyFood),false));
                    informationList.add(new Information("Did you know?", getString(R.string.huskyNote),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.huskhealth),false));
                    ratingBar1.setRating(4);
                    ratingBar2.setRating(5);
                    ratingBar3.setRating(5);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(5);
                    break;

                //White Swiss Shepherd
                case 19:sliderItems.add(new SliderItem(R.drawable.ws1));
                    sliderItems.add(new SliderItem(R.drawable.ws2));
                    sliderItems.add(new SliderItem(R.drawable.ws3));
                    viewpager2.setAdapter(new SliderAdapter(sliderItems,viewpager2));
                    informationList.add(new Information("History", getString(R.string.ws1),true));
                    informationList.add(new Information("Description", getString(R.string.ws2),true));
                    informationList.add(new Information("Appropriate Food",getString(R.string.ws3),false));
                    informationList.add(new Information("Did you know?", getString(R.string.ws4),false));
                    informationList.add(new Information("Health Consideration", getString(R.string.ws5),false));
                    ratingBar1.setRating(4);
                    ratingBar2.setRating(5);
                    ratingBar3.setRating(5);
                    ratingBar4.setRating(3);
                    ratingBar5.setRating(5);
                    ratingBar6.setRating(4);
                    ratingBar7.setRating(5);
                    break;
                }

            Intent intent = new Intent(this, ChartActivity.class);
            intent.putExtra(A, outputFeature0.getFloatArray()[0] + "0");
            intent.putExtra(B, outputFeature0.getFloatArray()[1] + "1");
            intent.putExtra(C, outputFeature0.getFloatArray()[2] + "2");
            intent.putExtra(D, outputFeature0.getFloatArray()[3] + "3");
            intent.putExtra(E, outputFeature0.getFloatArray()[4] + "4");
            intent.putExtra(F, outputFeature0.getFloatArray()[5] + "5");
            intent.putExtra(G, outputFeature0.getFloatArray()[6] + "6");
            intent.putExtra(H, outputFeature0.getFloatArray()[7] + "7");
            intent.putExtra(I, outputFeature0.getFloatArray()[8] + "8");
            intent.putExtra(J, outputFeature0.getFloatArray()[9] + "9");
            intent.putExtra(K, outputFeature0.getFloatArray()[10] + "10");
            intent.putExtra(L, outputFeature0.getFloatArray()[11] + "11");
            intent.putExtra(M, outputFeature0.getFloatArray()[12] + "12");
            intent.putExtra(N, outputFeature0.getFloatArray()[13] + "13");
            intent.putExtra(O, outputFeature0.getFloatArray()[14] + "14");
            intent.putExtra(P, outputFeature0.getFloatArray()[15] + "15");
            intent.putExtra(Q, outputFeature0.getFloatArray()[16] + "16");
            intent.putExtra(Z, outputFeature0.getFloatArray()[17] + "17");
            intent.putExtra(S, outputFeature0.getFloatArray()[18] + "18");
            intent.putExtra(U, outputFeature0.getFloatArray()[19] + "19");


            charBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });

            viewpager2.setClipToPadding(false);
            viewpager2.setClipChildren(false);
            viewpager2.setOffscreenPageLimit(3);
            viewpager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
            compositePageTransformer.addTransformer(new MarginPageTransformer(40));
            compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
                @Override
                public void transformPage(@NonNull View page, float position) {
                    float r = 1 - Math.abs(position);
                    page.setScaleY(0.85f + r * 0.15f);
                }
            });

            viewpager2.setPageTransformer(compositePageTransformer);
            model.close();



        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    int getMax(float[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > arr[max]) max = i;
        }
        return max;
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
                    imageView.setImageBitmap(bitmap);
                    bitmap = Bitmap.createScaledBitmap(bitmap, imageSize, imageSize, false);
                    classifyImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static final String A = "0";
    public static final String B = "1";
    public static final String C = "2";
    public static final String D = "3";
    public static final String E = "4";
    public static final String F = "5";
    public static final String G = "6";
    public static final String H = "7";
    public static final String I = "8";
    public static final String J = "9";
    public static final String K = "10";
    public static final String L = "11";
    public static final String M = "12";
    public static final String N = "13";
    public static final String O = "14";
    public static final String P = "15";
    public static final String Q = "16";
    public static final String Z = "17";
    public static final String S = "18";
    public static final String U = "19";



}

