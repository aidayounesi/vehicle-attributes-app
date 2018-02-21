package se.springworks.vehicleattributesapp.data;

import android.content.Context;
import android.support.annotation.NonNull;

import se.springworks.vehicleattributesapp.data.basic.Vehicle;
import se.springworks.vehicleattributesapp.network.VehiclesServiceApi;

/**
 * Created by aida on 2/16/18.
 *
 * Concrete implementation to loaded vehicle from the data source.
 * Singleton pattern
 */
public class LoadedVehiclesRepository implements VehiclesRepository {

    private Vehicle mCachedVehicle;

    private final VehiclesServiceApi mVehiclesServiceApi;
    private final Context mContext;

    private static LoadedVehiclesRepository mInstance;


    private LoadedVehiclesRepository(@NonNull VehiclesServiceApi vehiclesServiceApi, Context context) {
        mVehiclesServiceApi = vehiclesServiceApi;
        mContext = context;
    }

    public synchronized static LoadedVehiclesRepository getVehicleFromServerInstance(
            @NonNull VehiclesServiceApi vehiclesServiceApi, Context context) {
        if (null == mInstance) {
            mInstance = new LoadedVehiclesRepository(vehiclesServiceApi, context);
        }
        return mInstance;
    }

    @Override
    public void loadVehicle(@NonNull final GetVehicleCallback callback) {
        // Load from API only if needed.
        if (mCachedVehicle == null) {
            mVehiclesServiceApi.getVehicle(new VehiclesServiceApi.VehiclesServiceCallback<Vehicle>() {
                @Override
                public void onLoaded(Vehicle vehicle) {
                    mCachedVehicle = vehicle;
                    callback.onVehicleLoaded(vehicle);
                }

                @Override
                public void onError(String errorMsg) {
                    callback.onFailed(errorMsg);
                }
            }, mContext);
        } else {
            callback.onVehicleLoaded(mCachedVehicle);
        }
    }

    @Override
    public void refreshData() {
        mCachedVehicle = null;
    }

}
