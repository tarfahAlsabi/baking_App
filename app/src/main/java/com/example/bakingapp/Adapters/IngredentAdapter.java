package com.example.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bakingapp.Data.Ingredient;
import com.example.bakingapp.R;

import java.util.ArrayList;

public class IngredentAdapter extends RecyclerView.Adapter<IngredentAdapter.IngredentViewHolder> {
    Context context;
    ArrayList<Ingredient> ingredients;

    public IngredentAdapter (Context context,ArrayList<Ingredient> ingredients){
        this.context=context;
        this.ingredients=ingredients;
    }
    @NonNull
    @Override
    public IngredentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ingredientView = LayoutInflater.from(context).inflate(R.layout.recipe_ingredient_card, parent, false);

        return new IngredentViewHolder(ingredientView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredentViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        if(ingredients != null)
            return ingredients.size();
        return 0;
    }

    public class IngredentViewHolder extends RecyclerView.ViewHolder {
        TextView ingredient , quantity , measure;
        Ingredient ingredientObject;

        public IngredentViewHolder(View view){
            super(view);
            ingredient = view.findViewById(R.id.ingredients_ingredient);
            quantity =view.findViewById(R.id.ingredients_quantity);
            measure = view.findViewById(R.id.ingredients_measure);
        }

        public void bind(int position){
            ingredientObject = ingredients.get(position);
            ingredient.setText(ingredientObject.getIngredient());
            quantity.setText(String.valueOf(ingredientObject.getQuantity()));
            measure.setText(ingredientObject.getMeasure());
        }
    }
}
