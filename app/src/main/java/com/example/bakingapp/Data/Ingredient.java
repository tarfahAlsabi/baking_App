package com.example.bakingapp.Data;

public class Ingredient {
    double quantity;
    String measure;
    String ingredient;


    public Ingredient(double quantity,String measure, String ingredient){
        this.quantity=quantity;
        this.measure=measure;
        this.ingredient=ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
