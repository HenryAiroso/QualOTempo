package edu.udesc.qualotempo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> recipeAdapter;
    private ArrayList<String> recipeList;
    Button btn_searchMeal;
    EditText et_searchInput;
    ListView lv_recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeList = new ArrayList<>();

        btn_searchMeal = findViewById(R.id.btn_searchMeal);
        et_searchInput = findViewById(R.id.et_searchInput);
        lv_recipeList = findViewById(R.id.lv_recipeList);

        recipeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recipeList);
        lv_recipeList.setAdapter(recipeAdapter);

        btn_searchMeal.setOnClickListener(v -> {
            String mealName = et_searchInput.getText().toString();
            GetRecipeData getRecipeData = new GetRecipeData(this);
            getRecipeData.getRecipeData(mealName);
        });
    }

    public void updateRecipeList(String recipe) {
        recipeList.add(recipe);
        recipeAdapter.notifyDataSetChanged();
    }
}