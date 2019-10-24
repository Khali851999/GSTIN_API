
package com.akshat.swdm_android_task;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Addr implements Parcelable {

    @SerializedName("bno")
    @Expose
    private String bno;
    @SerializedName("dst")
    @Expose
    private String dst;
    @SerializedName("stcd")
    @Expose
    private String stcd;
    @SerializedName("loc")
    @Expose
    private String loc;
    @SerializedName("lt")
    @Expose
    private String lt;
    @SerializedName("lg")
    @Expose
    private String lg;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("bnm")
    @Expose
    private String bnm;
    @SerializedName("flno")
    @Expose
    private String flno;
    @SerializedName("pncd")
    @Expose
    private String pncd;
    @SerializedName("st")
    @Expose
    private String st;

    protected Addr(Parcel in) {
        bno = in.readString();
        dst = in.readString();
        stcd = in.readString();
        loc = in.readString();
        lt = in.readString();
        lg = in.readString();
        city = in.readString();
        bnm = in.readString();
        flno = in.readString();
        pncd = in.readString();
        st = in.readString();
    }

    public static final Creator<Addr> CREATOR = new Creator<Addr>() {
        @Override
        public Addr createFromParcel(Parcel in) {
            return new Addr(in);
        }

        @Override
        public Addr[] newArray(int size) {
            return new Addr[size];
        }
    };

    public String getBno() {
        return bno;
    }

    public void setBno(String bno) {
        this.bno = bno;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLt() {
        return lt;
    }

    public void setLt(String lt) {
        this.lt = lt;
    }

    public String getLg() {
        return lg;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBnm() {
        return bnm;
    }

    public void setBnm(String bnm) {
        this.bnm = bnm;
    }

    public String getFlno() {
        return flno;
    }

    public void setFlno(String flno) {
        this.flno = flno;
    }

    public String getPncd() {
        return pncd;
    }

    public void setPncd(String pncd) {
        this.pncd = pncd;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(bno);
        parcel.writeString(dst);
        parcel.writeString(stcd);
        parcel.writeString(loc);
        parcel.writeString(lt);
        parcel.writeString(lg);
        parcel.writeString(city);
        parcel.writeString(bnm);
        parcel.writeString(flno);
        parcel.writeString(pncd);
        parcel.writeString(st);
    }
}
