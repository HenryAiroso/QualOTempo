package edu.udesc.qualotempo;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private ArrayList<Food> foodList;
    private Context context;

    public FoodAdapter(ArrayList<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {
        Food food = foodList.get(position);

        // Set the values of the TextViews
        holder.tv_recipeName.setText(food.getStrMeal());
        holder.tv_recipeCategory.setText(food.getStrCategory());
        holder.tv_recipeInstructions.setText(food.getStrInstructions());

        // Set an OnClickListener for the delete button
        holder.btn_deleteRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the delete button click event
                // You can delete the item from the list or perform any other action here
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        public View btn_deleteRecipe;
        TextView tv_recipeName;
        TextView tv_recipeCategory;
        TextView tv_recipeInstructions;
        TextView tv_recipeIngredients;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_recipeName = itemView.findViewById(R.id.tv_recipeName);
            tv_recipeCategory = itemView.findViewById(R.id.tv_recipeCategory);
            tv_recipeInstructions = itemView.findViewById(R.id.tv_recipeInstructions);
            tv_recipeIngredients = itemView.findViewById(R.id.tv_recipeIngredients);
        }
    }
}