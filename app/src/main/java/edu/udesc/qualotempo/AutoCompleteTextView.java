package edu.udesc.qualotempo;

import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteTextView {
    private MainActivity mainActivity;
    private ArrayAdapter<String> adapter;

    public AutoCompleteTextView(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setAdapter(ArrayAdapter<String> adapter) {
        this.adapter = adapter;
    }

    public void createAutoCompleteDictionary(String url) {
        // Make a network request to the provided URL
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parse the response and extract the suggestions
                            List<String> suggestions = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                String suggestion = response.getString(i);
                                suggestions.add(suggestion);
                            }

                            // Populate the adapter with the suggestions
                            adapter.clear();
                            adapter.addAll(suggestions);

                            // Set the adapter to the AutoCompleteTextView
                            mainActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mainActivity.getAutoCompleteTextView().setAdapter(adapter);
                                }
                            });
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

        // Add the request to the Volley request queue
        Volley.newRequestQueue(mainActivity).add(request);
    }
}