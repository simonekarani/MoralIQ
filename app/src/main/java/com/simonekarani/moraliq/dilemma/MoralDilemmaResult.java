//
//  MoralDilemmaResult.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.dilemma;

public class MoralDilemmaResult {
    private int dilemmaIdx;
    private int userOptIdx;

    public MoralDilemmaResult(int qIdx, int optIdx) {
        this.dilemmaIdx = qIdx;
        this.userOptIdx = optIdx;
    }
}
