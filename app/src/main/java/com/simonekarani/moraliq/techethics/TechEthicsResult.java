//  TechEthicsResult.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.techethics;

import android.os.Parcel;
import android.os.Parcelable;

public class TechEthicsResult implements Parcelable {
    private int techEthicsIdx;
    private int userOptIdx;

    public TechEthicsResult(int qIdx, int optIdx) {
        this.techEthicsIdx = qIdx;
        this.userOptIdx = optIdx;
    }
    public int getTechEthicsIdx() { return this.techEthicsIdx; }
    public int getUserOptIdx() { return this.userOptIdx; }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        dest.writeInt(techEthicsIdx);
        dest.writeInt(userOptIdx);
    }

    public TechEthicsResult(Parcel in) {
        techEthicsIdx = in.readInt();
        userOptIdx = in.readInt();
    }

    public static final Parcelable.Creator<TechEthicsResult> CREATOR = new Parcelable.Creator<TechEthicsResult>() {
        public TechEthicsResult createFromParcel(Parcel in) {
            return new TechEthicsResult(in);
        }

        public TechEthicsResult[] newArray(int size) {
            return new TechEthicsResult[size];
        }
    };
}
