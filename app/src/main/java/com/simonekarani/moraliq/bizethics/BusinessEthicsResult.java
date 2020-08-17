//
//  BusinessEthicsResult.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.bizethics;

public class BusinessEthicsResult {
    private int bizEthicsIdx;
    private int userOptIdx;

    public BusinessEthicsResult(int qIdx, int optIdx) {
        this.bizEthicsIdx = qIdx;
        this.userOptIdx = optIdx;
    }
}
