
package com.akshat.swdm_android_task;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Compliance implements Parcelable {

    @SerializedName("filingFrequency")
    @Expose
    private Object filingFrequency;

    protected Compliance(Parcel in) {
    }

    public static final Creator<Compliance> CREATOR = new Creator<Compliance>() {
        @Override
        public Compliance createFromParcel(Parcel in) {
            return new Compliance(in);
        }

        @Override
        public Compliance[] newArray(int size) {
            return new Compliance[size];
        }
    };

    public Object getFilingFrequency() {
        return filingFrequency;
    }

    public void setFilingFrequency(Object filingFrequency) {
        this.filingFrequency = filingFrequency;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
