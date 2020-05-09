package com.example.fitness_31;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class splashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            EasySplashScreen config = new EasySplashScreen(splashScreenActivity.this)
                    .withFullScreen()
                    .withTargetActivity(MainActivity.class)
                    .withSplashTimeOut(2000)
                    .withLogo(R.drawable.lco_transperent)
                    .withBackgroundColor(Color.WHITE)
                    .withAfterLogoText("Fitness App")
                    .withFooterText("by Partha Prateem Patra");

            //Here all the parameters have been set
//                    .withBackgroundColor(Color.parseColor("#1a1b29"))

            //Set the color of the text
            config.getAfterLogoTextView().setTextColor(Color.WHITE);
            config.getFooterTextView().setTextColor(Color.WHITE);

            //Creating the splash screen
            View view = config.create();
            setContentView(view);
            // Sets content view to initial splash screen
        }
    }

