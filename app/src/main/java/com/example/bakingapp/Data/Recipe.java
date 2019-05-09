package com.example.bakingapp.Data;

import java.util.ArrayList;

public class Recipe {
    int id;
    String name;
    ArrayList<Ingredient> ingredients;
    ArrayList<Step> steps;
    int servings;
    String image;

    public Recipe(int id, String name, ArrayList<Ingredient> ingredients, ArrayList<Step>  steps,
                  int servings, String image){
        this.id=id;
        this.name=name;
        this.ingredients=ingredients;
        this.steps=steps;
        this.servings=servings;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getServings() {
        return servings;
    }

    public  ArrayList<Step>  getSteps() {
        return steps;
    }

    public String getImage() {
        return image;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSteps( ArrayList<Step>  steps) {
        this.steps = steps;
    }
}
