package com.simonekarani.moraliq.medethics;

import android.os.Parcel;
import android.os.Parcelable;

public class MedEthicalResult implements Parcelable {
    private int dilemmaIdx;
    private int userOptIdx;

    public MedEthicalResult(int qIdx, int optIdx) {
        this.dilemmaIdx = qIdx;
        this.userOptIdx = optIdx;
    }

    public int getMedEthicalIdx() { return this.dilemmaIdx; }
    public int getUserOptIdx() { return this.userOptIdx; }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        dest.writeInt(dilemmaIdx);
        dest.writeInt(userOptIdx);
    }

    public MedEthicalResult(Parcel in) {
        dilemmaIdx = in.readInt();
        userOptIdx = in.readInt();
    }

    public static final Parcelable.Creator<MedEthicalResult> CREATOR = new Parcelable.Creator<MedEthicalResult>() {
        public MedEthicalResult createFromParcel(Parcel in) {
            return new MedEthicalResult(in);
        }

        public MedEthicalResult[] newArray(int size) {
            return new MedEthicalResult[size];
        }
    };
}
