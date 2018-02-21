package se.springworks.vehicleattributesapp.data.basic;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vehicle {

    @SerializedName("regno")
    @Expose
    private String regno;
    @SerializedName("vin")
    @Expose
    private String vin;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("emission")
    @Expose
    private Emission emission;
    @SerializedName("fuel")
    @Expose
    private Fuel fuel;
    @SerializedName("gearbox_type")
    @Expose
    private String gearboxType;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("fuel_types")
    @Expose
    private List<String> fuelTypes = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Vehicle() {
    }

    /**
     * 
     * @param timestamp
     * @param gearboxType
     * @param emission
     * @param fuelTypes
     * @param vin
     * @param regno
     * @param brand
     * @param fuel
     * @param year
     */
    public Vehicle(String regno, String vin, String timestamp, Emission emission, Fuel fuel, String gearboxType, Integer year, String brand, List<String> fuelTypes) {
        super();
        this.regno = regno;
        this.vin = vin;
        this.timestamp = timestamp;
        this.emission = emission;
        this.fuel = fuel;
        this.gearboxType = gearboxType;
        this.year = year;
        this.brand = brand;
        this.fuelTypes = fuelTypes;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Emission getEmission() {
        return emission;
    }

    public void setEmission(Emission emission) {
        this.emission = emission;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getFuelTypes() {
        return fuelTypes;
    }

    public void setFuelTypes(List<String> fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

}
