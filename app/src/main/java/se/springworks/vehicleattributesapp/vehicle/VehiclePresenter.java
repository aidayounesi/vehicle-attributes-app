package se.springworks.vehicleattributesapp.vehicle;

import android.content.Context;
import android.support.annotation.NonNull;
import se.springworks.vehicleattributesapp.data.VehiclesRepository;
import se.springworks.vehicleattributesapp.data.basic.Vehicle;

/**
 * Created by aida on 2/14/18.
 *
 * Listens to user actions from the UI ({@link VehicleFragment}), retrieves the data and updates
 * the UI as required.
 */

public class VehiclePresenter implements VehicleContract.UserActionsListener {

    private VehiclesRepository mVehiclesRepository;

    private VehicleContract.View mVehicleDetailView;

    public VehiclePresenter(@NonNull VehiclesRepository vehiclesRepository,
                                   @NonNull VehicleContract.View vehicleDetailView) {
        mVehiclesRepository = vehiclesRepository;
        mVehicleDetailView = vehicleDetailView;
    }

    @Override
    public void requestVehicleDetails(boolean forceUpdate) {
        mVehicleDetailView.setProgressIndicator(true);
        if (forceUpdate) {
            mVehiclesRepository.refreshData();
        }
            mVehiclesRepository.loadVehicle(new VehiclesRepository.GetVehicleCallback() {
                @Override
                public void onVehicleLoaded(Vehicle vehicle) {
                    mVehicleDetailView.setProgressIndicator(false);
                    mVehicleDetailView.showVehicleDetails(vehicle);
                }

                @Override
                public void onFailed(String errorMsg) {
                    mVehicleDetailView.setProgressIndicator(false);
                    mVehicleDetailView.showErrorForLoading(errorMsg);
                }
            });

    }
}
