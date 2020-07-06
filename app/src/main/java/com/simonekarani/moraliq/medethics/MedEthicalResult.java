package com.simonekarani.moraliq.medethics;

public class MedEthicalResult {
    private int dilemmaIdx;
    private int userOptIdx;

    public MedEthicalResult(int qIdx, int optIdx) {
        this.dilemmaIdx = qIdx;
        this.userOptIdx = optIdx;
    }
}
