package edu.udesc.qualotempo;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GetRecipeData{
    private Context context;
    private RequestQueue requestQueue;
    private List<RecipeModel> recipeList = new ArrayList<>();
    public GetRecipeData(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    public void getRecipeData(String mealName) {
        String url = "https://api.example.com/recipes?meal=" + mealName;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray recipesArray = response.getJSONArray("recipes");
                            List<RecipeModel> recipes = new ArrayList<>();

                            for (int i = 0; i < recipesArray.length(); i++) {
                                JSONObject recipeObject = recipesArray.getJSONObject(i);
                                String name = recipeObject.getString("name");
                                String category = recipeObject.getString("category");
                                String instructions = recipeObject.getString("instructions");

                                RecipeModel recipe = new RecipeModel(name, category, instructions);
                                recipes.add(recipe);
                            }

                            // Update the recipeList and notify the adapter
                            recipeList.clear();
                            recipeList.addAll(recipes);
                            ArrayAdapter<Object> adapter = null;
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        requestQueue.add(request);
    }
}
