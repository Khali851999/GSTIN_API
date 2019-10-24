
package com.akshat.swdm_android_task;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaxpayerInfo implements Parcelable {

    @SerializedName("dty")
    @Expose
    private String dty;
    @SerializedName("tradeNam")
    @Expose
    private String tradeNam;
    @SerializedName("adadr")
    @Expose
    private List<Object> adadr = null;
    @SerializedName("ctb")
    @Expose
    private String ctb;
    @SerializedName("rgdt")
    @Expose
    private String rgdt;
    @SerializedName("lgnm")
    @Expose
    private String lgnm;
    @SerializedName("ctjCd")
    @Expose
    private String ctjCd;
    @SerializedName("gstin")
    @Expose
    private String gstin;
    @SerializedName("pradr")
    @Expose
    private Pradr pradr;
    @SerializedName("lstupdt")
    @Expose
    private String lstupdt;
    @SerializedName("nba")
    @Expose
    private List<String> nba = null;
    @SerializedName("ctj")
    @Expose
    private String ctj;
    @SerializedName("stjCd")
    @Expose
    private String stjCd;
    @SerializedName("sts")
    @Expose
    private String sts;
    @SerializedName("cxdt")
    @Expose
    private String cxdt;
    @SerializedName("stj")
    @Expose
    private String stj;

    protected TaxpayerInfo(Parcel in) {
        dty = in.readString();
        tradeNam = in.readString();
        ctb = in.readString();
        rgdt = in.readString();
        lgnm = in.readString();
        ctjCd = in.readString();
        gstin = in.readString();
        pradr = in.readParcelable(Pradr.class.getClassLoader());
        lstupdt = in.readString();
        nba = in.createStringArrayList();
        ctj = in.readString();
        stjCd = in.readString();
        sts = in.readString();
        cxdt = in.readString();
        stj = in.readString();
    }

    public static final Creator<TaxpayerInfo> CREATOR = new Creator<TaxpayerInfo>() {
        @Override
        public TaxpayerInfo createFromParcel(Parcel in) {
            return new TaxpayerInfo(in);
        }

        @Override
        public TaxpayerInfo[] newArray(int size) {
            return new TaxpayerInfo[size];
        }
    };

    public String getDty() {
        return dty;
    }

    public void setDty(String dty) {
        this.dty = dty;
    }

    public String getTradeNam() {
        return tradeNam;
    }

    public void setTradeNam(String tradeNam) {
        this.tradeNam = tradeNam;
    }

    public List<Object> getAdadr() {
        return adadr;
    }

    public void setAdadr(List<Object> adadr) {
        this.adadr = adadr;
    }

    public String getCtb() {
        return ctb;
    }

    public void setCtb(String ctb) {
        this.ctb = ctb;
    }

    public String getRgdt() {
        return rgdt;
    }

    public void setRgdt(String rgdt) {
        this.rgdt = rgdt;
    }

    public String getLgnm() {
        return lgnm;
    }

    public void setLgnm(String lgnm) {
        this.lgnm = lgnm;
    }

    public String getCtjCd() {
        return ctjCd;
    }

    public void setCtjCd(String ctjCd) {
        this.ctjCd = ctjCd;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public Pradr getPradr() {
        return pradr;
    }

    public void setPradr(Pradr pradr) {
        this.pradr = pradr;
    }

    public String getLstupdt() {
        return lstupdt;
    }

    public void setLstupdt(String lstupdt) {
        this.lstupdt = lstupdt;
    }

    public List<String> getNba() {
        return nba;
    }

    public void setNba(List<String> nba) {
        this.nba = nba;
    }

    public String getCtj() {
        return ctj;
    }

    public void setCtj(String ctj) {
        this.ctj = ctj;
    }

    public String getStjCd() {
        return stjCd;
    }

    public void setStjCd(String stjCd) {
        this.stjCd = stjCd;
    }

    public String getSts() {
        return sts;
    }

    public void setSts(String sts) {
        this.sts = sts;
    }

    public String getCxdt() {
        return cxdt;
    }

    public void setCxdt(String cxdt) {
        this.cxdt = cxdt;
    }

    public String getStj() {
        return stj;
    }

    public void setStj(String stj) {
        this.stj = stj;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dty);
        parcel.writeString(tradeNam);
        parcel.writeString(ctb);
        parcel.writeString(rgdt);
        parcel.writeString(lgnm);
        parcel.writeString(ctjCd);
        parcel.writeString(gstin);
        parcel.writeParcelable(pradr, i);
        parcel.writeString(lstupdt);
        parcel.writeStringList(nba);
        parcel.writeString(ctj);
        parcel.writeString(stjCd);
        parcel.writeString(sts);
        parcel.writeString(cxdt);
        parcel.writeString(stj);
    }
}
