package com.example.csnea;

public class CalorieController {
    private String Username;
    private String nameoffood;
    private float numberofcals;
    private float fatpercentage;
    private float proteinpercentage;

    public CalorieController (String Username, String nameoffood, float numberofcals, float fatpercentage, float proteinpercentage){
        this.Username = Username;
        this.nameoffood = nameoffood;
        this.numberofcals = numberofcals;
        this.fatpercentage = fatpercentage;
        this.proteinpercentage = proteinpercentage;
    }

    public String getUsername() {return Username;}

    public void setUsername(String username) {Username = username;}

    public String getNameoffood() {return nameoffood;}

    public void setNameoffood(String nameoffood) {this.nameoffood = nameoffood;}

    public float getNumberofcals() {return numberofcals;}

    public void setNumberofcals(float numberofcals) {this.numberofcals = numberofcals;}

    public float getFatpercentage() {return fatpercentage;}

    public void setFatpercentage(float fatpercentage) {this.fatpercentage = fatpercentage;}

    public float getProteinpercentage() {return proteinpercentage;}

    public void setProteinpercentage(float proteinpercentage) {this.proteinpercentage = proteinpercentage;}
}
