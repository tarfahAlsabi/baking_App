package com.example.bakingapp.Utilites;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bakingapp.Adapters.IngredentAdapter;
import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.MainActivity;
import com.example.bakingapp.R;

public class IngredientFragment extends Fragment {
Recipe recipe;
Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View rootView = inflater.inflate(R.layout.activity_recipe_ingredent, container, false);
        RecyclerView ingredentRV = rootView.findViewById(R.id.ingredients_recycler_view);
        LinearLayoutManager layoutManager= new LinearLayoutManager(context);
        Bundle bundle = getArguments();
        int id = bundle.getInt("recipeID",1);
        recipe = MainActivity.recipeArrayList.get(id - 1);
        IngredentAdapter adapter = new IngredentAdapter(context,recipe.getIngredients());
        Log.i("NO ingredient: ",adapter.getItemCount()+"");
        ingredentRV.setLayoutManager(layoutManager);
        ingredentRV.setAdapter(adapter);
        return rootView;
    }
}
