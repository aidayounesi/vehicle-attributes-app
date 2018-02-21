package se.springworks.vehicleattributesapp.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import se.springworks.vehicleattributesapp.data.basic.Vehicle;

/**
 * Created by aida on 2/16/18.
 *
 * Implementation of the Vehicles Service API.
 */
public class VehiclesServiceApiImpl implements VehiclesServiceApi {

    private static final String URL = "https://gist.githubusercontent.com/sommestad/e38c1acf2aed495edf2d/raw/cdb6dfb85101eedad60853c44266249a3f4ac5df/vehicle-attributes.json";
    private static Vehicle vehicle;

    @Override
    public void getVehicle(final VehiclesServiceCallback<Vehicle> callback, Context context) {

        RequestQueue queue = NetworkSingleton.getInstance(context).
                getRequestQueue();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        Gson gson = new Gson();
                        vehicle = gson.fromJson(response, Vehicle.class);
                        callback.onLoaded(vehicle);
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
