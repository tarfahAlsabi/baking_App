package com.example.bakingapp.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.Data.Step;
import com.example.bakingapp.R;
import com.example.bakingapp.StepDetails;
import com.example.bakingapp.Utilites.StepFragment;

import java.util.ArrayList;

public class RecipeDetailsAdapet extends RecyclerView.Adapter<RecipeDetailsAdapet.RecipeDetailsAdapetViewHolder> {
    ArrayList<Step> steps;
    Context context;
    int recipeID;
    FragmentManager fragmentManager;
    boolean land ;

    public RecipeDetailsAdapet(Context context,ArrayList<Step> steps,int recipeID , FragmentManager fragmentManager , boolean land){
        this.context=context;
        this.steps=steps;
        this.recipeID=recipeID;
        this.fragmentManager = fragmentManager;
        this.land =land;
    }

    @NonNull
    @Override
    public RecipeDetailsAdapetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stepsView = LayoutInflater.from(context).inflate(R.layout.recipe_details_steps_card, parent, false);
        return new RecipeDetailsAdapetViewHolder(stepsView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeDetailsAdapetViewHolder holder, int position) {
         holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(steps != null)
        return steps.size();
        return 0;
    }


    public class RecipeDetailsAdapetViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView stepShprtDescription;
        Step step;

        public RecipeDetailsAdapetViewHolder(View view){
            super(view);
            stepShprtDescription = view.findViewById(R.id.recipe_steps_card);
            view.setOnClickListener(this);
        }

        public void bind(int position){
            step = steps.get(position);
            stepShprtDescription.setText(step.getShortDescription());
        }
        @Override
        public void onClick(View v)
        {

            if(land){
                Bundle bundle = new Bundle();
                bundle.putInt("stepID",step.getId());
                bundle.putInt("recipeID",recipeID);
                StepFragment stepFragment = new StepFragment();
                stepFragment.setContext(context);
                stepFragment.setArguments(bundle);

                fragmentManager.beginTransaction().replace(R.id.step_details_fragment_tablet ,stepFragment).commit();
            }else {
                Log.i("step Clicked", step.getShortDescription());
                Intent stepIntent = new Intent(context, StepDetails.class);
                stepIntent.putExtra("recipeID", recipeID);
                stepIntent.putExtra("stepID", step.getId());
                context.startActivity(stepIntent);
            }
        }
    }
}
