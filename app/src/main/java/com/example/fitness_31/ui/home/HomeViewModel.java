package com.example.fitness_31.ui.home;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.fitness_31.MainActivity;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> reps;


    public HomeViewModel(int n) {
        reps = new MutableLiveData<>();
        reps.setValue(String.valueOf(n));

    }

    public LiveData<String> getText() {
        return reps;
    }
}