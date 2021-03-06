package se.springworks.vehicleattributesapp.data.basic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fuel {

    @SerializedName("gasoline")
    @Expose
    private Gasoline gasoline;

    @SerializedName("diesel")
    @Expose
    private Diesel diesel;


    /**
     * No args constructor for use in serialization
     * 
     */
    public Fuel() {
    }

    /**
     * 
     * @param gasoline
     */
    public Fuel(Gasoline gasoline) {
        super();
        this.gasoline = gasoline;
    }

    /**
     *
     * @param diesel
     */
    public Fuel(Diesel diesel) {
        super();
        this.diesel = diesel;
    }

    /**
     *
     * @param gasoline
     * @param diesel
     */
    public Fuel(Gasoline gasoline, Diesel diesel) {
        super();
        this.gasoline = gasoline;
        this.diesel = diesel;
    }

    public Diesel getDiesel() {
        return diesel;
    }

    public void setDiesel(Diesel diesel) {
        this.diesel = diesel;
    }
    public Gasoline getGasoline() {
        return gasoline;
    }

    public void setGasoline(Gasoline gasoline) {
        this.gasoline = gasoline;
    }

}
