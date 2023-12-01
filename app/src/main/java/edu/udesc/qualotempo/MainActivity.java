package edu.udesc.qualotempo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> recipeAdapter;
    private ArrayList<String> recipeList;
    Button btn_getCityID, btn_getWeatherByCityID, get_WeatherByCityName;
    EditText et_dataInput;
    ListView lv_weatherReports;

    private static final String API_KEY = ApiKeys.OPEN_WEATHER_MAP_API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeList = new ArrayList<>();
        recipeAdapter = new ArrayAdapter<>(this, R.layout.list_item_recipe, R.id.tv_recipeName, recipeList);
        lv_weatherReports.setAdapter(recipeAdapter);

        btn_getCityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByCityID = findViewById(R.id.btn_getWeatherByCityID);
        get_WeatherByCityName = findViewById(R.id.get_WeatherByCityName);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReports = findViewById(R.id.lv_weatherReports);

        btn_getCityID.setOnClickListener(v -> {
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            String mealName = et_dataInput.getText().toString();
            String mealUrl = "https://www.themealdb.com/api/json/v1/1/search.php?s=" + mealName;

            JsonObjectRequest mealRequest = new JsonObjectRequest(Request.Method.GET, mealUrl, null,
                    response -> {
                        try {
                            JSONArray mealsArray = response.getJSONArray("meals");

                            if (mealsArray.length() > 0) {
                                JSONObject mealObject = mealsArray.getJSONObject(0);
                                String foundMealName = mealObject.getString("strMeal");
                                String mealCategory = mealObject.getString("strCategory");
                                String mealInstructions = mealObject.getString("strInstructions");

                                // Update your UI accordingly
                                Toast.makeText(MainActivity.this, "Meal Name: " + foundMealName + "\nCategory: " + mealCategory + "\nInstructions: " + mealInstructions, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Meal not found", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    });

            queue.add(mealRequest);
        });

        get_WeatherByCityName.setOnClickListener(v -> {
            // Handle the click event for get_WeatherByCityName
            // Add your code here
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            String cityName = et_dataInput.getText().toString();
            String weatherUrl = "https://api.openweathermap.org/data/1.0/onecall?q=" + cityName + "&exclude=minutely,hourly,daily&appid=" + API_KEY;

            // Create a JsonObjectRequest with the GET method for weather data.
            JsonObjectRequest weatherRequest = new JsonObjectRequest(Request.Method.GET, weatherUrl, null,
                    response -> {
                        // Handle the response here
                        // This method will be called when the request is successful
                        try {
                            // Parse the JSON response
                            // Handle the response here
                            // This method will be called when the request is successful
                            JSONObject currentWeather = response.getJSONObject("current");
                            String weatherDescription = currentWeather.getJSONArray("weather").getJSONObject(0).getString("description");
                            double temperature = currentWeather.getDouble("temp");

                            // Update your UI accordingly
                            Toast.makeText(MainActivity.this, "Weather: " + weatherDescription + ", Temperature: " + temperature, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        // Handle the error here
                        // This method will be called when there is an error in the request
                        Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    });

            // Add the weather request to the RequestQueue.
            queue.add(weatherRequest);
        });
}}
