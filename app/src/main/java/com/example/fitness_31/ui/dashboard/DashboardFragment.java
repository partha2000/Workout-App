package com.example.fitness_31.ui.dashboard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitness_31.Database.WorkoutDatabase;
import com.example.fitness_31.R;
import com.example.fitness_31.model.globalTracker;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.text.DateFormat.SHORT;

public class DashboardFragment extends Fragment {

//    private BarChart chart;
    private Calendar today;
    private LineChart chart;
    private TextView time_spent,calories_burnt;
//    private WebView fire_gif;

//    private LottieAnimationView animationView;
//    private JSONObject input;

//    private ArrayList<globalTracker> recordList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

//        chart = root.findViewById(R.id.barChart);
        chart =  root.findViewById(R.id.line_chart);
        time_spent = root.findViewById(R.id.time_spent_field);
        calories_burnt = root.findViewById(R.id.calories);
        this.today = today.getInstance();

//        fire_gif = root.findViewById(R.id.fire_gif);
//        WebSettings webSettings = fire_gif.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        String file = "file:android_asset/fire_cal.gif";
//        fire_gif.loadUrl(file);

        addingLineData();

        WorkoutDatabase handler = new WorkoutDatabase(this.getContext());
        String input = DateFormat.getDateInstance(SHORT)
                .format(this.today.getTime());
        globalTracker record = handler.getRecord(input);
        time_spent.setText("Time spent today = "+(int) record.getTimeWorkedOut()+" s");
        calories_burnt.setText("Calories burnt = "+(int) (record.getTimeWorkedOut()*2)+" cals");


//        input = String.valueOf(R.string.fire_animatio);
//        animationView = root.findViewById(R.id.animation_view);
//        animationView.setAnimation(input);
//        animationView.playAnimation();

        return root;
    }




//    private void addingBarData() {
//
////        YourData[] dataObjects = ...;
//        WorkoutDatabase handler = new WorkoutDatabase(this.getContext());
//        List<globalTracker> recordList = handler.getAllRecords();
//
//        List<BarEntry> entries = new ArrayList<>();
//        for (globalTracker data : recordList) {
//            // turn your data into Entry objects
//            entries.add(new BarEntry(data.getId(), (float) data.getTimeWorkedOut()));
////            entries.add(new Entry(data.getId(), data.getTimeWorkedOut()));
//        }
//
//        BarDataSet dataSet = new BarDataSet(entries, "Total Workout Time (min)"); // add entries to dataset
//        dataSet.setColor(Color.parseColor("#673AB7"));
//        dataSet.setValueTextColor(R.color.black_overlay);
//        // styling, ...
//
////        chart.setBackgroundColor(R.drawable.gradient_background);
//
//
//        chart.animateY(3000, Easing.EaseOutBounce);
//        BarData barData = new BarData(dataSet);
//        chart.setData(barData);
//        chart.invalidate(); // refresh
//    }

    private void addingLineData() {

        //        YourData[] dataObjects = ...;
        WorkoutDatabase handler = new WorkoutDatabase(this.getContext());
        List<globalTracker> recordList = handler.getAllRecords();

        List<Entry> lineentries = new ArrayList<>();
        for (globalTracker data : recordList) {
            // turn your data into Entry objects
            lineentries.add(new Entry((float)data.getId(),(float) data.getTimeWorkedOut()));
            LineDataSet dataSet = new LineDataSet(lineentries, "workout time"); // add entries to dataset
            dataSet.setColor(Color.parseColor("#673AB7"));

            dataSet.setValueTextColor(Color.BLACK); // styling, ...
            LineData lineData = new LineData(dataSet);
            dataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
//            dataSet.setGradientColor(R.color.colorPrimary,R.color.colorPrimaryDark);
            dataSet.setGradientColor(Color.parseColor("#673AB7"),Color.BLACK);

            chart.animateY(2000, Easing.EaseInOutQuad);
            chart.setData(lineData);
            chart.invalidate(); // refresh

        }
    }
}