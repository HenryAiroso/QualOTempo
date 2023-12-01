package edu.udesc.qualotempo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecipeService {
    private List<RecipeModel> recipeDatabase;

    public RecipeService() {
        // Initialize the recipe database
        recipeDatabase = new ArrayList<>();
        loadRecipesFromJson();
    }

    private void loadRecipesFromJson() {
        try (Reader reader = new FileReader("meals.json")) {
            // Define the type of the list using Gson's TypeToken
            Type recipeListType = new TypeToken<List<RecipeModel>>() {}.getType();

            // Parse the JSON file into a list of RecipeModel objects
            List<RecipeModel> recipes = new Gson().fromJson(reader, recipeListType);

            // Add the recipes to the recipeDatabase list
            recipeDatabase.addAll(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<RecipeModel> loadRecipes(String searchInput, int currentPage) {
        // Implement the logic to load recipes based on the search input and current page
        // For simplicity, this example returns a fixed number of recipes from the recipe database
        int recipesPerPage = 3;
        int startIndex = (currentPage - 1) * recipesPerPage;
        int endIndex = Math.min(startIndex + recipesPerPage, recipeDatabase.size());
        return recipeDatabase.subList(startIndex, endIndex);
    }

    public List<RecipeModel> getRecipesByMealName(String mealName) {
        // Implement the logic to retrieve recipes based on the meal name
        // For simplicity, this example returns all recipes that contain the meal name in their name or category
        List<RecipeModel> matchingRecipes = new ArrayList<>();
        for (RecipeModel recipe : recipeDatabase) {
            if (recipe.contains(mealName)) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
    }
}