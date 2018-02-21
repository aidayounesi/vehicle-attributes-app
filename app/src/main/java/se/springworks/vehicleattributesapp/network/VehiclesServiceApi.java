package se.springworks.vehicleattributesapp.network;

import android.content.Context;


import se.springworks.vehicleattributesapp.data.basic.Vehicle;

/**
 * Created by aida on 2/16/18.
 *
 * Defines an interface to the service API that is used by this application. All data request should
 * be piped through this interface.
 */
public interface VehiclesServiceApi {

    interface VehiclesServiceCallback<T> {

        void onLoaded(T Vehicle);
        void onError(String errorMsg);
    }

    void getVehicle(VehiclesServiceCallback<Vehicle> callback, Context context);
}
