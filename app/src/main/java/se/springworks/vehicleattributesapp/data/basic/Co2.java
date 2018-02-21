package se.springworks.vehicleattributesapp.data.basic;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Co2 {

    @SerializedName("urban")
    @Expose
    private Double urban;
    @SerializedName("rural")
    @Expose
    private Double rural;
    @SerializedName("mixed")
    @Expose
    private Double mixed;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Co2() {
    }

    /**
     *
     * @param urban
     * @param mixed
     * @param rural
     */
    public Co2(@Nullable Double urban, @Nullable Double rural, @Nullable Double mixed) {
        super();
        this.urban = urban;
        this.rural = rural;
        this.mixed = mixed;
    }

    public Double getUrban() {
        return urban;
    }

    public void setUrban(Double urban) {
        this.urban = urban;
    }

    public Double getRural() {
        return rural;
    }

    public void setRural(Double rural) {
        this.rural = rural;
    }

    public Double getMixed() {
        return mixed;
    }

    public void setMixed(Double mixed) {
        this.mixed = mixed;
    }

}
