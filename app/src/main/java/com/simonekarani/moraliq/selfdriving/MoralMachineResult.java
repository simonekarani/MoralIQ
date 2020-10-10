package com.simonekarani.moraliq.selfdriving;

import android.os.Parcel;
import android.os.Parcelable;

public class MoralMachineResult implements Parcelable {
    private int mMachineDataIdx;
    private int userOptIdx;

    public MoralMachineResult(int dataListIdx, int userSelectionIdx) {
        this.mMachineDataIdx = dataListIdx;
        this.userOptIdx = userSelectionIdx;
    }
    public int getmMachineDataIdx() { return mMachineDataIdx; }
    public int getUserOptIdx() { return userOptIdx; }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        dest.writeInt(mMachineDataIdx);
        dest.writeInt(userOptIdx);
    }

    public MoralMachineResult(Parcel in) {
        mMachineDataIdx = in.readInt();
        userOptIdx = in.readInt();
    }

    public static final Parcelable.Creator<MoralMachineResult> CREATOR = new Parcelable.Creator<MoralMachineResult>() {
        public MoralMachineResult createFromParcel(Parcel in) {
            return new MoralMachineResult(in);
        }

        public MoralMachineResult[] newArray(int size) {
            return new MoralMachineResult[size];
        }
    };
}
