package com.example.bakingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.Data.Step;
import com.example.bakingapp.Utilites.JSONUtilites;
import com.example.bakingapp.Utilites.PlayerFragmetn;
import com.example.bakingapp.Utilites.StepFragment;

import java.util.ArrayList;


public class StepDetails extends AppCompatActivity {
Step step;
int stepID;
ArrayList<Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        Intent stepIntent = getIntent();

        if(stepIntent.hasExtra("stepID")){
            int recipeID = JSONUtilites.recepId;
            stepID = stepIntent.getIntExtra("stepID",1);
            Bundle bundle = new Bundle();
            bundle.putInt("stepID",stepID);
            bundle.putInt("recipeID",recipeID);
            StepFragment stepFragment = new StepFragment();
            stepFragment.setContext(this);
            stepFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.steps_container,stepFragment).commit();
        }
    }

}
