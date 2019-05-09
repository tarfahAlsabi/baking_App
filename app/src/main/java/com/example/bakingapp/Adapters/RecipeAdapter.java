package com.example.bakingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.R;
import com.example.bakingapp.RecipeDetails;
import com.example.bakingapp.Utilites.JSONUtilites;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    ArrayList<Recipe> recipes;
    Context context;

    public RecipeAdapter(Context context, ArrayList<Recipe> resources){
        this.context = context;
        this.recipes = resources;
    }
    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recipeView = LayoutInflater.from(context).inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(recipeView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(recipes != null)
        return recipes.size();
        return 0;
    }
    public void setRecipes(ArrayList<Recipe> recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView recipeImage;
        TextView recipeName;
        TextView recipeServing;
        Recipe recipe;
        public RecipeViewHolder(View view){
            super(view);
            recipeImage = view.findViewById(R.id.recipe_card_image);
            recipeName = view.findViewById(R.id.recipe_card_name);
            view.setOnClickListener(this);
            recipeServing = view.findViewById(R.id.recipe_card_NO_serving);
        }

        public void bind (int index){
            recipe = recipes.get(index);
            recipeName.setText(recipe.getName());
            recipeServing.setText(recipe.getServings()+"");
            if(recipe.getImage() == null || recipe.getImage().isEmpty() ){
                    AssetManager assetManager = context.getAssets();
                    try {
                        InputStream istr = assetManager.open("image-not-found.jpg");

//                       Picasso.with(context).load(istr.read()).into(recipeImage);
                        Drawable d = Drawable.createFromStream(istr, null);
                        recipeImage.setImageDrawable(d);
                        recipeImage.setContentDescription("the recipe has no image");
                        istr.close();
                    }catch (IOException e){
                        Log.i("NotFoundImage","Error in downloading the image");
                    }
            }else{
                Log.i("mm","there is image");
                recipeImage.setImageURI(Uri.parse(recipe.getImage()));
                recipeImage.setContentDescription(recipe.getName() + " image");
            }
        }
        @Override
        public void onClick(View v) {
            Log.i("viewHolder OnClick: ",recipe.getName());
            Intent recipeDetailsIntent = new Intent(context, RecipeDetails.class);
            JSONUtilites.recepId = recipe.getId();
            recipeDetailsIntent.putExtra("ID",recipe.getId());
            context.startActivity(recipeDetailsIntent);
        }
    }
}
