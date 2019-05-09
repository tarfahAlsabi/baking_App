package com.example.bakingapp;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.bakingapp.Data.Ingredient;
import com.example.bakingapp.Utilites.JSONUtilites;
import com.google.android.exoplayer2.C;

import java.util.ArrayList;


public class recipeViewIngredients implements RemoteViewsService.RemoteViewsFactory {

    ArrayList<Ingredient> ingredients;
    Context context;
    public recipeViewIngredients(Context context ){
        this.context = context;
            }
    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        ingredients = JSONUtilites.recipes.get(JSONUtilites.recepId).getIngredients();

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if(ingredients ==  null  || ingredients.isEmpty()){
            return null;
        }
        Ingredient ingredient = ingredients.get(position);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredient_card);
        views.setTextViewText(R.id.ingredients_ingredient,ingredient.getIngredient());
        views.setTextViewText(R.id.ingredients_measure,ingredient.getMeasure());
        views.setTextViewText(R.id.ingredients_quantity,String.valueOf(ingredient.getQuantity()));

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
