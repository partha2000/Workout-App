package com.example.fitness_31;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fitness_31.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements HomeFragment.FragmentListener {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        context=this.getApplicationContext();


    }




    @Override
    public void onInputSent(String input, int choice) {

        switch (choice){
            case 1:
                Intent intent= new Intent(this.getApplicationContext(),random_mode_exercises_recyclerview.class);
                intent.putExtra("repetation",input);
                Log.d("Repetaion sent= ",input);
                startActivity(intent);
                break;
            case 2:

                Intent intent1= new Intent(this.getApplicationContext(),DailyMode_RecyclerView.class);
                intent1.putExtra("repetation",input);
                Log.d("Repetaion sent= ",input);
                startActivity(intent1);
                break;

        }

        }

    }

