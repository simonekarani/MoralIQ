package com.simonekarani.moraliq.selfdriving;

public class MoralMachineModel {
    public int optFontSize;
    private String option1Text;
    private int option1ResId;
    private String option2Text;
    private int option2ResId;

    public MoralMachineModel(int fontSize, String opt1, int opt1ResId, String opt2, int opt2ResId) {
        this.optFontSize = fontSize;
        this.option1Text = opt1;
        this.option1ResId = opt1ResId;
        this.option2Text = opt2;
        this.option2ResId = opt2ResId;
    }

    public int getOptFontSize() { return this.optFontSize; }
    public String getOption1() {
        return this.option1Text;
    }
    public int getOption1ResId() { return this.option1ResId; }
    public String getOption2() {
        return this.option2Text;
    }
    public int getOption2ResId() { return this.option2ResId; }
}
