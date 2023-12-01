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

public class getWeatherData {
    private double latitude;
    private double longitude;
    private MainActivity mainActivity;

    public getWeatherData(MainActivity mainActivity, double latitude, double longitude) {
        this.mainActivity = mainActivity;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void GetMealData() {
        String mealUrl = "https://www.themealdb.com/api/json/v1/1/random.php";

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(mainActivity);

        // Create a JsonObjectRequest with the GET method for meal data.
        JsonObjectRequest mealRequest = new JsonObjectRequest(Request.Method.GET, mealUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response here
                        // This method will be called when the request is successful
                        try {
                            // Parse the JSON response
                            JSONArray meals = response.getJSONArray("meals");
                            JSONObject meal = meals.getJSONObject(0);
                            String mealName = meal.getString("strMeal");
                            String mealCategory = meal.getString("strCategory");
                            String mealInstructions = meal.getString("strInstructions");

                            // Update your UI accordingly
                            Toast.makeText(mainActivity, "Meal: " + mealName + ", Category: " + mealCategory + ", Instructions: " + mealInstructions, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error here
                // This method will be called when there is an error in the request
                Toast.makeText(mainActivity, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Add the meal request to the RequestQueue.
        queue.add(mealRequest);
    }
}