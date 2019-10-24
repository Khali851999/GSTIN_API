
package com.akshat.swdm_android_task;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pradr implements Parcelable {

    @SerializedName("ntr")
    @Expose
    private String ntr;
    @SerializedName("addr")
    @Expose
    private Addr addr;

    protected Pradr(Parcel in) {
        ntr = in.readString();
    }

    public static final Creator<Pradr> CREATOR = new Creator<Pradr>() {
        @Override
        public Pradr createFromParcel(Parcel in) {
            return new Pradr(in);
        }

        @Override
        public Pradr[] newArray(int size) {
            return new Pradr[size];
        }
    };

    public String getNtr() {
        return ntr;
    }

    public void setNtr(String ntr) {
        this.ntr = ntr;
    }

    public Addr getAddr() {
        return addr;
    }

    public void setAddr(Addr addr) {
        this.addr = addr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ntr);
    }
}
