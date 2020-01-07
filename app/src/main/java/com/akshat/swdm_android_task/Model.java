
package com.akshat.swdm_android_task;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model implements Parcelable {

    @SerializedName("compliance")
    @Expose
    private Compliance compliance;
    @SerializedName("filing")
    @Expose
    private List<Object> filing = null;
    @SerializedName("taxpayerInfo")
    @Expose
    private TaxpayerInfo taxpayerInfo;

    protected Model(Parcel in) {
    }

    public static final Creator<Model> CREATOR = new Creator<Model>() {
        @Override
        public Model createFromParcel(Parcel in) {
            return new Model(in);
        }

        @Override
        public Model[] newArray(int size) {
            return new Model[size];
        }
    };

    public Compliance getCompliance() {
        return compliance;
    }

    public void setCompliance(Compliance compliance) {
        this.compliance = compliance;
    }

    public List<Object> getFiling() {
        return filing;
    }

    public void setFiling(List<Object> filing) {
        this.filing = filing;
    }

    public TaxpayerInfo getTaxpayerInfo() {
        return taxpayerInfo;
    }

    public void setTaxpayerInfo(TaxpayerInfo taxpayerInfo) {
        this.taxpayerInfo = taxpayerInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
