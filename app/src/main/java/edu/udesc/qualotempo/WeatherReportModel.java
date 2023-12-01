package edu.udesc.qualotempo;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherReportModel {
    private int id;
    private String weatherStateName;

    public WeatherReportModel(int id, String weatherStateName) {
        this.id = id;
        this.weatherStateName = weatherStateName;
    }

    public int getId() {
        return id;
    }

    public String getWeatherStateName() {
        return weatherStateName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWeatherStateName(String weatherStateName) {
        this.weatherStateName = weatherStateName;
    }

    public static void main(String[] args) {
        // Parse the JSON response and create an instance of WeatherReportModel
        try {
            String jsonResponse = "{\"coord\":{\"lon\":-0.1257,\"lat\":51.5085},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}],\"base\":\"stations\",\"main\":{\"temp\":272,\"feels_like\":270.02,\"temp_min\":270.44,\"temp_max\":273.03,\"pressure\":1005,\"humidity\":91},\"visibility\":10000,\"wind\":{\"speed\":1.54,\"deg\":320},\"clouds\":{\"all\":0},\"dt\":1701401279,\"sys\":{\"type\":2,\"id\":2075535,\"country\":\"GB\",\"sunrise\":1701416607,\"sunset\":1701446142},\"timezone\":0,\"id\":2643743,\"name\":\"London\",\"cod\":200}";

            JSONObject responseJson = new JSONObject(jsonResponse);

            // Extract the values from the JSON response
            int id = responseJson.getInt("id");
            String weatherStateName = responseJson.getJSONArray("weather").getJSONObject(0).getString("main");

            // Create an instance of WeatherReportModel
            WeatherReportModel weatherReport = new WeatherReportModel(id, weatherStateName);

            // Use the weatherReport object as needed
            System.out.println("ID: " + weatherReport.getId());
            System.out.println("Weather State Name: " + weatherReport.getWeatherStateName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}