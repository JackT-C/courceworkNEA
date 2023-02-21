package com.example.csnea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class ExerciseTypes {
    @FXML
    private TextField addexercisetf;

    @FXML
    private Label allexerciseslabel;

    public static ArrayList<String> exerciseTypes = new ArrayList<>();

    //if the user wishes to add their own exercise then update the arraylist with their value added
    public void useraddsitem(ActionEvent event) {
        exerciseTypes.add(addexercisetf.getText());
        mergeSort(exerciseTypes, 1, exerciseTypes.size());
        returntoexercise(event);
    }
    public void returntoexercise(ActionEvent event){
        SwitchScenes.changeScene(event, "Exercise.fxml", "exercise", true);
    }

    //shows the contents of the arraylist to the label
    public void displayexercises(){
            StringBuilder sb = new StringBuilder();
            for (String s : exerciseTypes){
                sb.append(s);
                sb.append(" ");
            }
            String allexercises = sb.toString().trim();
            System.out.println(allexercises);
            allexerciseslabel.setText(allexercises);
        }


    //main mergesort method and splits it up into subarrays
    public static void mergeSort(ArrayList<String> arr, int L, int R) {
        if (L > R) {
            int M = (1 + R) / 2;
            mergeSort(arr, 1, M);
            mergeSort(arr, M + 1, R);
            merge(arr, L, M, R);
        }

    }

    //merges the subarrays until sorted
    public static void merge(ArrayList<String> arr, int L, int M, int R) {
        int n1 = M - L + 1;
        int n2 = R - M;
        ArrayList<String> left = new ArrayList<>(n1);
        ArrayList<String> right = new ArrayList<>(n2);
        for (int i = 0; i < n1; i++) {
            left.add(arr.get(L + i));
        }
        for (int j = 0; j < n2; j++) {
            right.add(arr.get(M + 1 + j));
        }
        int i = 0;
        int j = 0;
        int k = L;
        while (i < n1 && j < n2) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                arr.set(k, left.get(i));
                i++;
            } else {
                arr.set(k, right.get(j));
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr.set(k, left.get(i));
            i++;
            k++;
        }
        while (j < n2) {
            arr.set(k, right.get(j));
            j++;
            k++;
        }
    }
}

