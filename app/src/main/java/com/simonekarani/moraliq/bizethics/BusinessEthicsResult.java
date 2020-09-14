//
//  BusinessEthicsResult.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.bizethics;

import android.os.Parcel;
import android.os.Parcelable;

public class BusinessEthicsResult implements Parcelable {
    private int bizEthicsIdx;
    private int userOptIdx;

    public BusinessEthicsResult(int qIdx, int optIdx) {
        this.bizEthicsIdx = qIdx;
        this.userOptIdx = optIdx;
    }

    public int getBizEthicsIdx() { return this.bizEthicsIdx; }
    public int getUserOptIdx() { return this.userOptIdx; }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        dest.writeInt(bizEthicsIdx);
        dest.writeInt(userOptIdx);
    }

    public BusinessEthicsResult(Parcel in) {
        bizEthicsIdx = in.readInt();
        userOptIdx = in.readInt();
    }

    public static final Parcelable.Creator<BusinessEthicsResult> CREATOR = new Parcelable.Creator<BusinessEthicsResult>() {
        public BusinessEthicsResult createFromParcel(Parcel in) {
            return new BusinessEthicsResult(in);
        }

        public BusinessEthicsResult[] newArray(int size) {
            return new BusinessEthicsResult[size];
        }
    };
}
