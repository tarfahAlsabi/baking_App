package com.example.bakingapp.Utilites;

import com.example.bakingapp.Data.Ingredient;
import com.example.bakingapp.Data.Recipe;
import com.example.bakingapp.Data.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class JSONUtilites {

public static ArrayList<Recipe> recipes= new ArrayList<>();
public static int recepId;

    public static void getRecipes(String result) {
        try {
            JSONArray recipesJSON = new JSONArray(result);
            for(int i=0 ; i< recipesJSON.length();i++){
                JSONObject recipe = recipesJSON.getJSONObject(i);
                Recipe recipe1 = createRecipe(recipe);
                recipes.add(recipe1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static Recipe createRecipe(JSONObject recipe)  throws JSONException {
        if(recipe == null)
            return null;
        int id = recipe.getInt("id");
        String name = recipe.getString( "name");
        int servings = recipe.getInt("servings");
        String image = recipe.getString("image");
        ArrayList<Ingredient> ingredients = getIngredients(recipe.getJSONArray("ingredients"));
        ArrayList<Step> steps = getSteps(recipe.getJSONArray("steps"));

        return new Recipe(id,name,ingredients,steps,servings,image);
    }

    private static ArrayList<Step> getSteps(JSONArray steps) throws JSONException {
        ArrayList<Step> stepsList = new ArrayList<>();
        for(int i=0;i<steps.length() ;i++) {
            JSONObject stepJSONObject = steps.getJSONObject(i);
            int id = stepJSONObject.getInt("id");
            String shortDescription = stepJSONObject.getString("shortDescription");
            String description = stepJSONObject.getString("description");
            String videoURL = stepJSONObject.getString("videoURL");
            String thumbnailURL = stepJSONObject.getString("thumbnailURL");
            stepsList.add(new Step(id,shortDescription,description,videoURL,thumbnailURL));

        }
        return stepsList;
     }

    private static ArrayList<Ingredient> getIngredients(JSONArray ingredients) throws JSONException{
        ArrayList<Ingredient> ingredientsList = new ArrayList<>();

        for(int i=0;i<ingredients.length() ;i++){
           JSONObject ingredientJSONObject = ingredients.getJSONObject(i);
            double quantity = ingredientJSONObject.getDouble("quantity");
            String measure = ingredientJSONObject.getString("measure");
            String ingredient = ingredientJSONObject.getString("ingredient");
            ingredientsList.add(new Ingredient(quantity,measure,ingredient));
        }
        return ingredientsList;
    }
}
