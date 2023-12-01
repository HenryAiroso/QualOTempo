package edu.udesc.qualotempo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private List<String> recipeList;
    private OnItemClickListener onItemClickListener;

    public RecipeAdapter(List<String> recipeList, OnItemClickListener onItemClickListener) {
        this.recipeList = recipeList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String recipe = recipeList.get(position);
        holder.recipeTextView.setText(recipe);

        // Adjust card size based on the length of the recipe text plus one paragraph
        ViewGroup.LayoutParams layoutParams = holder.cardView.getLayoutParams();
        int maxCardHeight = 100000; // Maximum height for the card
        int cardHeight = Math.max((holder.recipeTextView.getLineCount() + 1) * holder.recipeTextView.getLineHeight(), maxCardHeight); layoutParams.height = cardHeight; holder.cardView.setLayoutParams(layoutParams);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    if (recipe != null && !recipe.isEmpty()) {
                        onItemClickListener.onItemClick(recipe);
                    } else {
                        Toast.makeText(v.getContext(), "Ih, não vai ter não!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(String recipe);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardView;
        TextView recipeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            recipeTextView = itemView.findViewById(R.id.tv_recipeName);
        }
    }
}