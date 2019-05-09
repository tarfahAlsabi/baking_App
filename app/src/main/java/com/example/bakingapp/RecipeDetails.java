package com.example.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.bakingapp.Adapters.IngredentAdapter;
import com.example.bakingapp.Adapters.RecipeDetailsAdapet;
import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.Utilites.IngredientFragment;
import com.example.bakingapp.Utilites.JSONUtilites;

import java.util.ArrayList;

public class RecipeDetails extends AppCompatActivity {
Recipe recipe;
TextView recipeIngredent;
Context  context= this;
boolean tablet = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        Intent recipeDetailsIntent = getIntent();
        if(recipeDetailsIntent.hasExtra("ID")){
            int id = recipeDetailsIntent.getIntExtra("ID",1);
            recipe = JSONUtilites.recipes.get(id - 1);
            RecyclerView recyclerView = findViewById(R.id.recipe_details_card_recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            ScrollView scrollView = findViewById(R.id.recipe_details_tablet);
            if(scrollView != null){
                tablet =true;
            }
            RecipeDetailsAdapet recipeDetailsAdapet = new RecipeDetailsAdapet(this,recipe.getSteps(),id,getSupportFragmentManager(),tablet);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(recipeDetailsAdapet);
            Log.i("NO.steps: ",recipeDetailsAdapet.getItemCount()+"");
        }
        recipeIngredent = findViewById(R.id.recipe_steps_card);
        recipeIngredent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tablet){
                    Bundle bundle = new Bundle();
                    bundle.putInt("recipeID",recipe.getId());
                    IngredientFragment fragment = new IngredientFragment();
                    fragment.setArguments(bundle);
                    fragment.setContext(context);
                    getSupportFragmentManager().beginTransaction().replace(R.id.step_details_fragment_tablet , fragment).commit();
                }else {
                    Log.i("clicked", "clicked on ingredent");
                    Intent intent = new Intent(context, RecipeIngredent.class);
                    intent.putExtra("recipeID", recipe.getId());
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("recipeID",recipe.getId());
    }
}
