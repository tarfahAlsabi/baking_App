package com.example.bakingapp;

import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class recipeWidgetProvider extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new recipeViewIngredients(this.getApplicationContext());
    }
}
