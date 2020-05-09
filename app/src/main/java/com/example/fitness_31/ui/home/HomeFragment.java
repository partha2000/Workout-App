package com.example.fitness_31.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.fitness_31.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    //Edit this interface to send more items.
    public interface FragmentListener{
        void onInputSent(String input,int choice);
    }
    private FragmentListener listener;
    private int choice=0;
    private HomeViewModel homeViewModel;
    private CardView random_mode_button;
    private CardView daily_button;

    public String rep;
    private Button repetation_button;

    private EditText repetationValue;


    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        random_mode_button = root.findViewById(R.id.random_card);
        daily_button = root.findViewById(R.id.daily_card);
        context = this.getContext();

        random_mode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Random button: ","Clicked");
                createPopupDialog();
                Log.d("Repetations are = ", String.valueOf(rep));


            }
        });
        daily_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onInputSent("15",2);
            }
        });


        return root;
    }

    private void createPopupDialog() {
        builder =new AlertDialog.Builder(context);
        final View view =getLayoutInflater().inflate(R.layout.repetation_dialog,null);

        repetationValue = view.findViewById(R.id.repetation_value);
        repetation_button = view.findViewById(R.id.repetation_button);

        builder.setView(view);
        dialog=builder.create();
        dialog.show();
        rep = "0";

            repetation_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Repetation Button: ","pressed");
                    rep = repetationValue.getText().toString().trim();
                    if (rep.isEmpty()){
                        Snackbar.make(view,"Enter the repetation value", BaseTransientBottomBar.LENGTH_SHORT).show();
                        repetation_button.setEnabled(false);
                    }
                    else {
                        listener.onInputSent(rep,1);
                        dialog.dismiss();
                    }
                    repetation_button.setEnabled(true);
                }
            });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener){
            listener = (FragmentListener) context;
        }
        else
            throw  new RuntimeException(context.toString()+
                    " Must implement FragmentListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;

    }
}