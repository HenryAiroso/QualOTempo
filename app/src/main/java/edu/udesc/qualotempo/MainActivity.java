package edu.udesc.qualotempo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Food> foodList = new ArrayList<>();
    private FoodAdapter foodAdapter;
    private TextInputEditText etSearchInput;
    private Button btnSearchMeal;
    private List<RecipeModel> recipeList;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            setContentView(R.layout.activity_main);

            RecyclerView recyclerView = findViewById(R.id.rv_recipeList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            recipeList = new ArrayList<>();
            adapter = new RecipeAdapter(recipeList); // Initialize the adapter
            recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeList = new ArrayList<>();
        adapter = new RecipeAdapter(recipeList);
        recyclerView.setAdapter(adapter);

        btnSearchMeal = findViewById(R.id.btn_searchMeal); // Remove the data type declaration here
        btnSearchMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText etSearchInput = findViewById(R.id.et_searchInput);
                String searchInput = etSearchInput.getText().toString();

                Toast.makeText(MainActivity.this, "Button clicked: " + searchInput, Toast.LENGTH_SHORT).show();

                List<RecipeModel> filteredRecipes = filterRecipes(searchInput);

                recipeList.clear();
                recipeList.addAll(filteredRecipes);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private List<RecipeModel> filterRecipes(String searchInput) {
        List<RecipeModel> filteredRecipes = new ArrayList<>();

        for (RecipeModel recipe : recipeList) {
            if (recipe.getName().toLowerCase().contains(searchInput.toLowerCase()) ||
                    recipe.getCategory().toLowerCase().contains(searchInput.toLowerCase())) {
                filteredRecipes.add(recipe);
            }
        }

        return filteredRecipes;
    }

    private void performSearch(String mealName) {
        GetRecipeData getRecipeData = new GetRecipeData(MainActivity.this);
        getRecipeData.getRecipeData(mealName);
    }

    public void updateFoodList(List<Food> foodList) {
        this.foodList.clear();
        this.foodList.addAll(foodList);
        foodAdapter.notifyDataSetChanged();
    }
}