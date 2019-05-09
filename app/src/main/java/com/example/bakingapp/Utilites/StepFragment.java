package com.example.bakingapp.Utilites;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bakingapp.Data.Step;
import com.example.bakingapp.MainActivity;
import com.example.bakingapp.R;

import java.util.ArrayList;

public class StepFragment extends Fragment {
    Step step;
    int stepID;
    ArrayList<Step> steps;
    TextView stepInstrucation;
    Button nextBtn, previouseBtn;
    Context context;

    public void setContext(Context context){
        this.context =context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.step_fragment, container, false);
        Bundle bundle = getArguments();
        int recipeId= bundle.getInt("recipeID",1);
        stepID = bundle.getInt("stepID",0);
        steps = MainActivity.recipeArrayList.get(recipeId-1).getSteps();


        stepInstrucation = rootView.findViewById(R.id.step_instraction);
        nextBtn = rootView.findViewById(R.id.next_step_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stepID < steps.size()){
                    stepID++;
                    setStep();
                }

            }
        });
        previouseBtn =rootView.findViewById(R.id.previouse_step_btn);
        previouseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stepID > 0){
                    stepID--;
                    setStep();
                }
            }
        });

        setStep();
        return rootView;
    }

    private void setStep() {
        step = steps.get(stepID);
        stepInstrucation.setText(step.getDescription());
        PlayerFragmetn playerFragmetn = new PlayerFragmetn();
        playerFragmetn.setVaribels(context,step.getVideoURL());
        FragmentManager fragmentManager= getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.step_video_frame,playerFragmetn).commit();
        updatBtns();
    }

    private void updatBtns(){
        if(stepID == 0){
            previouseBtn.setClickable(false);
        }else {
            previouseBtn.setClickable(true);

        }
        if(stepID == (steps.size()-1) ){
            nextBtn.setClickable(false);
        }else {
            nextBtn.setClickable(true);
        }
    }
}

