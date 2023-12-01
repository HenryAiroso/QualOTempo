package edu.udesc.qualotempo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

public class GetRecipeData {
    private MainActivity mainActivity;

    public GetRecipeData(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getRecipeData(String mealName) {
        String mealUrl = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + mealName;

        RequestQueue queue = Volley.newRequestQueue(mainActivity);

        JsonObjectRequest mealRequest = new JsonObjectRequest(Request.Method.GET, mealUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray mealsArray = response.getJSONArray("meals");

                            if (mealsArray.length() > 0) {
                                JSONObject mealObject = mealsArray.getJSONObject(0);
                                String foundMealName = mealObject.getString("strMeal");
                                String mealCategory = mealObject.getString("strCategory");
                                String mealInstructions = mealObject.getString("strInstructions");

                                String recipe = "Meal Name: " + foundMealName + "\nCategory: " + mealCategory + "\nInstructions: " + mealInstructions;
                                mainActivity.updateRecipeList(recipe);
                            } else {
                                Toast.makeText(mainActivity, "Meal not found", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mainActivity, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(mealRequest);
    }
}