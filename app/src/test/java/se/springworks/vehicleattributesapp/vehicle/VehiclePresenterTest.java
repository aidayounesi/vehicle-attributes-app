package se.springworks.vehicleattributesapp.vehicle;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import se.springworks.vehicleattributesapp.data.VehiclesRepository;
import se.springworks.vehicleattributesapp.data.basic.Vehicle;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link VehiclePresenter}
 */
public class VehiclePresenterTest {

    private static Vehicle vehicle = new Vehicle();

    @Mock
    private VehiclesRepository mVehiclesRepository;

    @Mock
    private VehicleContract.View mVehicleView;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<VehiclesRepository.GetVehicleCallback> mGetVehicleCallbackCaptor;

    private VehiclePresenter mVehiclePresenter;

    @Before
    public void setupVehiclesPresenter() {
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mVehiclePresenter = new VehiclePresenter(mVehiclesRepository, mVehicleView);
    }

    @Test
    public void getVehicleFromRepositoryAndLoadIntoView() {
        // Given an initialized NotesPresenter with initialized notes
        // When loading of Notes is requested
        mVehiclePresenter.requestVehicleDetails(true);

        // Callback is captured and invoked with stubbed notes
        verify(mVehiclesRepository).loadVehicle(mGetVehicleCallbackCaptor.capture());
        mGetVehicleCallbackCaptor.getValue().onVehicleLoaded(vehicle);

        // Then progress indicator is hidden and notes are shown in UI
        InOrder inOrder = Mockito.inOrder(mVehicleView);
        inOrder.verify(mVehicleView).setProgressIndicator(true);
        inOrder.verify(mVehicleView).setProgressIndicator(false);
        verify(mVehicleView).showVehicleDetails(vehicle);
    }

}
