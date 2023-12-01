package edu.udesc.qualotempo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;



public class ActivityMainBinding {
    private final View rootView;
    public final RecyclerView RecyclerViewFood;

    private ActivityMainBinding(View rootView, RecyclerView RecyclerViewFood) {
        this.rootView = rootView;
        this.RecyclerViewFood = RecyclerViewFood;
    }

    @NonNull
    public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    @NonNull
    public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, boolean attachToParent) {
        View rootView = inflater.inflate(R.layout.activity_main, parent, attachToParent);
        RecyclerView RecyclerViewFood = rootView.findViewById(R.id.rv_recipeList);
        return new ActivityMainBinding(rootView, RecyclerViewFood);
    }

    @NonNull
    public View getRoot() {
        return rootView;
    }
}