//
//  MoralDilemmaResult.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.dilemma;

import android.os.Parcel;
import android.os.Parcelable;

public class MoralDilemmaResult implements Parcelable {
    private int dilemmaIdx;
    private int userOptIdx;

    public MoralDilemmaResult(int qIdx, int optIdx) {
        this.dilemmaIdx = qIdx;
        this.userOptIdx = optIdx;
    }

    public int getDilemmaIdx() { return this.dilemmaIdx; }
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

    public MoralDilemmaResult(Parcel in) {
        dilemmaIdx = in.readInt();
        userOptIdx = in.readInt();
    }

    public static final Parcelable.Creator<MoralDilemmaResult> CREATOR = new Parcelable.Creator<MoralDilemmaResult>() {
        public MoralDilemmaResult createFromParcel(Parcel in) {
            return new MoralDilemmaResult(in);
        }

        public MoralDilemmaResult[] newArray(int size) {
            return new MoralDilemmaResult[1];
        }
    };
}
