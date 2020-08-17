//  TechEthicsResult.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.techethics;

public class TechEthicsResult {
    private int techEthicsIdx;
    private int userOptIdx;

    public TechEthicsResult(int qIdx, int optIdx) {
        this.techEthicsIdx = qIdx;
        this.userOptIdx = optIdx;
    }
}
