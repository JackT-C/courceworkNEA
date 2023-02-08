package com.example.csnea;

import java.util.ArrayList;

public class ExerciseTypes {
    public static ArrayList<String> exerciseTypes = new ArrayList<>();

    public static void additems(){
        exerciseTypes.add("walking");
        exerciseTypes.add("gym");
        exerciseTypes.add("running");
        exerciseTypes.add("athletics");
        exerciseTypes.add("football");
        exerciseTypes.add("swimming");
        exerciseTypes.add("cycling");
        exerciseTypes.add("tennis");
        exerciseTypes.add("golf");
        exerciseTypes.add("hockey");
        exerciseTypes.add("badminton");
        exerciseTypes.add("basketball");
    }

    public static void useraddsitem(){

    }




    public static void mergeSort(ArrayList<String> arr, int L, int R) {
        if (L > R) {
            int M = (1 + R)/2;
            mergeSort(arr, 1, M);
            mergeSort(arr, M + 1, R);
        }

    }
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

