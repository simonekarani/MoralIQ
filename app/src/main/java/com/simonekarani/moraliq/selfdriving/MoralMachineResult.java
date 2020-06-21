package com.simonekarani.moraliq.selfdriving;

public class MoralMachineResult {
    private int mMachineDataIdx;
    private int userOptIdx;

    public MoralMachineResult(int dataListIdx, int userSelectionIdx) {
        this.mMachineDataIdx = dataListIdx;
        this.userOptIdx = userSelectionIdx;
    }
}
