package se.springworks.vehicleattributesapp.data.basic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gasoline {

    @SerializedName("co2")
    @Expose
    private Co2 co2;

    @SerializedName("average_consumption")
    @Expose
    private AverageConsumption averageConsumption;

    @SerializedName("tank_volume")
    @Expose
    private Double tankVolume;

    /**
     * No args constructor for use in serialization
     *
     */
    public Gasoline() {
    }

    /**
     *
     * @param co2
     */
    public Gasoline(Co2 co2) {
        super();
        this.co2 = co2;
    }

    /**
     *
     * @param averageConsumption
     */
    public Gasoline(AverageConsumption averageConsumption) {
        super();
        this.averageConsumption = averageConsumption;
    }

    /**
     *
     * @param averageConsumption
     * @param tankVolume
     */
    public Gasoline(AverageConsumption averageConsumption, Double tankVolume) {
        super();
        this.averageConsumption = averageConsumption;
        this.tankVolume = tankVolume;
    }

    /**
     *
     * @param tankVolume
     */
    public Gasoline(Double tankVolume) {
        super();
        this.averageConsumption = averageConsumption;
    }

    public AverageConsumption getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(AverageConsumption averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public Co2 getCo2() {
        return co2;
    }

    public void setCo2(Co2 co2) {
        this.co2 = co2;
    }

    public Double getTankVolume() {
        return tankVolume;
    }

    public void setTankVolume(Double tankVolume) {
        this.tankVolume = tankVolume;
    }

}
