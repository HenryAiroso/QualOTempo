package edu.udesc.qualotempo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecipeAdapter recipeAdapter;
    private List<String> recipeList;
    private Button btn_searchMeal;
    private EditText et_searchInput;
    private RecyclerView rv_recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeList = new ArrayList<>();

        btn_searchMeal = findViewById(R.id.btn_searchMeal);
        et_searchInput = findViewById(R.id.et_searchInput);
        rv_recipeList = findViewById(R.id.rv_recipeList);

        recipeAdapter = new RecipeAdapter(recipeList, new RecipeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String recipe) {
                // Handle recipe item click
            }
        });

        rv_recipeList.setLayoutManager(new LinearLayoutManager(this));
        rv_recipeList.setAdapter(recipeAdapter);

        btn_searchMeal.setOnClickListener(v -> {
            String mealName = et_searchInput.getText().toString();
            if (mealName.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter a meal name", Toast.LENGTH_SHORT).show();
            } else {
                GetRecipeData getRecipeData = new GetRecipeData(MainActivity.this);
                getRecipeData.getRecipeData(mealName);
            }
        });
    }

    public void updateRecipeList(String recipe) {
        recipeList.add(recipe);
        recipeAdapter.notifyDataSetChanged();
    }

    public AutoCompleteTextView getAutoCompleteTextView() {
        return findViewById(R.id.autoCompleteTextView);
    }
}