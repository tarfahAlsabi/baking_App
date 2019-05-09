package com.example.bakingapp;

import android.content.Context;
import android.os.AsyncTask;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.example.bakingapp.Adapters.RecipeAdapter;
import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.Utilites.JSONUtilites;
import com.example.bakingapp.Utilites.NetworkUtilites;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    ProgressBar progressBar;
    RecyclerView recipesListRV;
    RecipeAdapter recipeAdapter;
    Context context= this;
    public static ArrayList<Recipe>  recipeArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        recipesListRV = findViewById(R.id.RecyclerView_recipes_card);
        ScrollView scrollView = findViewById(R.id.recipe_card_tablet);
        RecyclerView.LayoutManager layoutManager;
        if(scrollView != null){
            layoutManager = new GridLayoutManager(this,3);

        }else{
            layoutManager = new LinearLayoutManager(this);
        }
        recipesListRV.setLayoutManager(layoutManager);
        new FetchRecipes().execute();
        recipeAdapter = new RecipeAdapter(context, recipeArrayList);
        recipesListRV.setAdapter(recipeAdapter);
    }

    class FetchRecipes extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                NetworkUtilites.getRecipsListRequest();
            }catch (IOException e){
                e.printStackTrace();
                Log.i("MainActivity","ERROR in retrieve recipes");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.INVISIBLE);
             recipeArrayList = JSONUtilites.recipes;
             int count = recipeArrayList.size();
             recipeAdapter.setRecipes(recipeArrayList);
             Log.i("Result","No.recipes: "+count);
        }
    }


}
