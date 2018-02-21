package se.springworks.vehicleattributesapp.vehicle;

import android.content.Context;

import se.springworks.vehicleattributesapp.data.basic.Vehicle;

/**
 * Created by aida on 2/14/18.
 *
 * Contract between the view and the presenter.
 */

public class VehicleContract {

    interface View {
        void setProgressIndicator(boolean active);

        void showVehicleDetails(Vehicle vehicle);

        void showErrorForLoading(String error);
    }

    interface UserActionsListener {
        void requestVehicleDetails(boolean forceUpdate);
    }
}
