package se.springworks.vehicleattributesapp.vehicle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import se.springworks.vehicleattributesapp.R;
import se.springworks.vehicleattributesapp.data.LoadedVehiclesRepository;
import se.springworks.vehicleattributesapp.network.VehiclesServiceApiImpl;
import se.springworks.vehicleattributesapp.data.basic.Vehicle;

/**
 * Created by aida on 2/16/18.
 */

public class VehicleFragment extends Fragment implements VehicleContract.View {

    private VehicleContract.UserActionsListener mActionsListener;

    private View mRootView;

    public static VehicleFragment newInstance() {
        return new VehicleFragment();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActionsListener = new VehiclePresenter(
                LoadedVehiclesRepository.getVehicleFromServerInstance(new VehiclesServiceApiImpl(), getContext()),
                    this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_vehicle, container, false);
        // Pull-to-refresh
        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) mRootView.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.requestVehicleDetails(true);
            }
        });
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.requestVehicleDetails(false);
    }

    @Override
    public void setProgressIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(active);
            }
        });
    }

    @Override
    public void showErrorForLoading(String error) {

    }

    @Override
    public void showVehicleDetails(Vehicle vehicle) {
        ((TextView) mRootView.findViewById(R.id.textViewTimestamp)).setText(vehicle.getTimestamp());

        ((TextView) mRootView.findViewById(R.id.textViewRegno)).setText(vehicle.getRegno());

        ((TextView) mRootView.findViewById(R.id.textViewVin)).setText(vehicle.getVin());

        ((TextView) mRootView.findViewById(R.id.textViewYearModel)).setText(vehicle.getYear().toString());

        ((ImageView) mRootView.findViewById(R.id.imageViewBrand)).setImageResource(
                getResources().getIdentifier(vehicle.getBrand().toLowerCase(), "drawable", getActivity().getPackageName()));

        ((TextView) mRootView.findViewById(R.id.textViewGearBoxType)).setText(
                vehicle.getGearboxType());

        setFuelsInfo(vehicle);
        setEmissionAndConsumption(vehicle);
    }

    private void setFuelsInfo(Vehicle vehicle) {
        String text = "";
        if (vehicle.getFuelTypes().toString().matches(".*[dD]iesel.*")) {
            text += "Diesel ";
            if (vehicle.getFuel().getDiesel().getTankVolume() != null) {
                text += "("+vehicle.getFuel().getDiesel().getTankVolume().toString() + " " + getString(R.string.liter)+")\n";
            }
        }
        if (vehicle.getFuelTypes().toString().matches(".*[gG]asoline.*")) {
            text += "Gasoline ";
            if (vehicle.getFuel().getGasoline().getTankVolume() != null) {
                text += "("+vehicle.getFuel().getGasoline().getTankVolume().toString() + " " + getString(R.string.liter)+")";
            }
        }
        ((TextView)mRootView.findViewById(R.id.textViewFuel)).setText(text);

    }

    private void setEmissionAndConsumption(Vehicle vehicle) {

        LinearLayout linearLayoutEmissionConsumption = mRootView.findViewById(R.id.linearLayoutEmissionCommission);
        linearLayoutEmissionConsumption.removeAllViews();

        LayoutInflater mInflater = LayoutInflater.from(getActivity());

        View consumption = mInflater.inflate(R.layout.detail_fuel, null, false);
        ((TextView) consumption.findViewById(R.id.textViewHeaderTitle)).setText(getString(R.string.average_consumption));
        ((TextView) consumption.findViewById(R.id.textViewRuralUnit)).setText(getString(R.string.liters_meter));
        ((TextView) consumption.findViewById(R.id.textViewUrbanUnit)).setText(getString(R.string.liters_meter));
        ((TextView) consumption.findViewById(R.id.textViewMixedUnit)).setText(getString(R.string.liters_meter));

        View emission = mInflater.inflate(R.layout.detail_fuel, null, false);
        ((TextView) emission.findViewById(R.id.textViewHeaderTitle)).setText(getString(R.string.emission));
        ((TextView) emission.findViewById(R.id.textViewRuralUnit)).setText(getString(R.string.kg_meter));
        ((TextView) emission.findViewById(R.id.textViewUrbanUnit)).setText(getString(R.string.kg_meter));
        ((TextView) emission.findViewById(R.id.textViewMixedUnit)).setText(getString(R.string.kg_meter));

        if (vehicle.getFuelTypes().toString().matches(".*[dD]iesel.*")) {
            ((LinearLayout) emission.findViewById(R.id.linearLayoutDetailFuel)).addView(createFuelRow(
                    "Diesel",
                    vehicle.getEmission().getDiesel().getCo2().getRural(),
                    vehicle.getEmission().getDiesel().getCo2().getUrban(),
                    vehicle.getEmission().getDiesel().getCo2().getMixed()));
            ((LinearLayout) consumption.findViewById(R.id.linearLayoutDetailFuel)).addView(createFuelRow(
                    "Diesel",
                    vehicle.getFuel().getDiesel().getAverageConsumption().getRural(),
                    vehicle.getFuel().getDiesel().getAverageConsumption().getUrban(),
                    vehicle.getFuel().getDiesel().getAverageConsumption().getMixed()));
        }
        if (vehicle.getFuelTypes().toString().matches(".*[gG]asoline.*")) {
            ((LinearLayout) emission.findViewById(R.id.linearLayoutDetailFuel)).addView(createFuelRow(
                    "Gasoline",
                    vehicle.getEmission().getGasoline().getCo2().getRural(),
                    vehicle.getEmission().getGasoline().getCo2().getUrban(),
                    vehicle.getEmission().getGasoline().getCo2().getMixed()));
            ((LinearLayout) consumption.findViewById(R.id.linearLayoutDetailFuel)).addView(createFuelRow(
                    "Gasoline",
                    vehicle.getFuel().getGasoline().getAverageConsumption().getRural(),
                    vehicle.getFuel().getGasoline().getAverageConsumption().getUrban(),
                    vehicle.getFuel().getGasoline().getAverageConsumption().getMixed()));
        }
        linearLayoutEmissionConsumption.addView(consumption);
        linearLayoutEmissionConsumption.addView(emission);
    }

    private View createFuelRow(String title, Double rural, Double urban, Double mixed) {

        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View view = mInflater.inflate(R.layout.detail_fuel_row, null, false);
        setText(rural, R.id.textViewRowRural, view);
        setText(urban, R.id.textViewRowUrban, view);
        setText(mixed, R.id.textViewRowMixed, view);
        ((TextView)view.findViewById(R.id.textViewRowTitle)).setText(title);
        return view;
    }

    private void setText(Double value, int id, View parent) {
        if (value != null) {
            ((TextView) parent.findViewById(id)).setText((String.format ("%.6f", value)));
        }
        else {
            ((TextView) parent.findViewById(id)).setText("-");
        }
    }
}
