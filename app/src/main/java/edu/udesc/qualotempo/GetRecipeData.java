package edu.udesc.qualotempo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetRecipeData {
    private MainActivity mainActivity;
    private static final String MEAL_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";

    public GetRecipeData(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getRecipeData(String mealName) {
        String mealUrl = MEAL_URL + mealName;

        // Create a cache and network for the request queue
        Cache cache = new DiskBasedCache(mainActivity.getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());

        // Create a new request queue using the cache and network
        RequestQueue queue = new RequestQueue(cache, network);
        queue.start();

        JsonObjectRequest mealRequest = new JsonObjectRequest(Request.Method.GET, mealUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray mealsArray = response.getJSONArray("meals");

                            if (mealsArray.length() > 0) {
                                List<String> recipeList = new ArrayList<>();

                                for (int i = 0; i < mealsArray.length(); i++) {
                                    JSONObject mealObject = mealsArray.getJSONObject(i);
                                    String foundMealName = mealObject.getString("strMeal");
                                    String mealCategory = mealObject.getString("strCategory");
                                    String mealInstructions = mealObject.getString("strInstructions");

                                    String recipe = "Meal Name: " + foundMealName + "\nCategory: " + mealCategory + "\nInstructions: " + mealInstructions;
                                    recipeList.add(recipe);
                                }

                                // Update the recipe list in the main activity
                                mainActivity.updateRecipeList(recipeList.toString());
                            } else {
                                // Handle the case when the "meals" array is empty
                                Toast.makeText(mainActivity, "No meals found", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            // Handle the case when the "meals" value is null or not a JSON array
                            e.printStackTrace();
                            Toast.makeText(mainActivity, "Error: Invalid response format", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mainActivity, "Network error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the queue
        queue.add(mealRequest);
    }
}