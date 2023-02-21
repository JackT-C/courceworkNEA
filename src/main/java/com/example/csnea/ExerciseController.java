package com.example.csnea;

public class ExerciseController {
    private String Username;
    private String typeofexercise;
    private float caloriesburnt;
    private float hoursspent;

    public ExerciseController(String Username, String typeofexercise, float caloriesburnt, float hoursspent){
        this.Username = Username;
        this.typeofexercise = typeofexercise;
        this.caloriesburnt = caloriesburnt;
        this.hoursspent = hoursspent;
    }

    //getter and setter methods for the values in the database so that they can be added to the observable list

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getTypeofexercise() {
        return typeofexercise;
    }

    public void setTypeofexercise(String typeofexercise) {
        this.typeofexercise = typeofexercise;
    }

    public float getCaloriesburnt() {
        return caloriesburnt;
    }

    public void setCaloriesburnt(float caloriesburnt) {
        this.caloriesburnt = caloriesburnt;
    }

    public float getHoursspent() {
        return hoursspent;
    }

    public void setHoursspent(float hoursspent) {
        this.hoursspent = hoursspent;
    }
}
