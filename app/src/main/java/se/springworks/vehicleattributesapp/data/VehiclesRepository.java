package se.springworks.vehicleattributesapp.data;

import android.content.Context;
import android.support.annotation.NonNull;

import se.springworks.vehicleattributesapp.data.basic.Vehicle;

/**
 * Created by aida on 2/16/18.
 */
public interface VehiclesRepository {

    interface GetVehicleCallback {

        void onVehicleLoaded(Vehicle vehicle);
        void onFailed(String errorMsg);
    }

    void loadVehicle(@NonNull GetVehicleCallback callback);

    void refreshData();
}
