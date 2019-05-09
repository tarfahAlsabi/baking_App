package com.example.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.bakingapp.Adapters.IngredentAdapter;
import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.Utilites.IngredientFragment;

public class RecipeIngredent extends AppCompatActivity {
Recipe recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_ingredent);
        Intent intent= getIntent();
        if(intent.hasExtra("recipeID")){
            int id = intent.getIntExtra("recipeID",1);
           Bundle bundle= new Bundle();
           bundle.putInt("recipeID",id);
            IngredientFragment fragment = new IngredientFragment();
            fragment.setArguments(bundle);
            fragment.setContext(this);
            getSupportFragmentManager().beginTransaction().add(R.id.activity_recipe_ingredent,fragment);
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("recipeID",recipe.getId());
    }
}
