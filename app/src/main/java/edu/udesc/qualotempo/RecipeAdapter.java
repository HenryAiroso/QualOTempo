package edu.udesc.qualotempo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<RecipeModel> recipeList;

    public RecipeAdapter(List<RecipeModel> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        RecipeModel recipe = recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRecipeName;
        private TextView tvRecipeCategory;
        private TextView tvRecipeInstructions;
        private Button btnDeleteRecipe;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRecipeName = itemView.findViewById(R.id.tv_recipeName);
            tvRecipeCategory = itemView.findViewById(R.id.tv_recipeCategory);
            tvRecipeInstructions = itemView.findViewById(R.id.tv_recipeInstructions);
            btnDeleteRecipe = itemView.findViewById(R.id.btn_deleteRecipe);
        }

        public void bind(RecipeModel recipe) {
            tvRecipeName.setText(recipe.getName());
            tvRecipeCategory.setText(recipe.getCategory());
            tvRecipeInstructions.setText(recipe.getInstructions());

            btnDeleteRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle delete recipe button click
                }
            });
        }
    }
}
