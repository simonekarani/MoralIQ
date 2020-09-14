//
//  MoralDilemmaModel.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.dilemma;

public class MoralDilemmaModel {
    private int qFontSize;
    private int optFontSize;
    private int btnGap;
    private int analysisOpt;
    private String analysis;
    private String question;
    private Integer imgResId;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public MoralDilemmaModel(int qfont, int optfont, int btnGap, int analysisOpt, String analysis, String question, int imgId, String opt1, String opt2, String opt3, String opt4) {
        this.qFontSize = qfont;
        this.optFontSize = optfont;
        this.btnGap = btnGap;
        this.analysisOpt = analysisOpt;
        this.analysis = analysis;
        this.question = question;
        this.imgResId = imgId;
        this.option1 = opt1;
        this.option2 = opt2;
        this.option3 = opt3;
        this.option4 = opt4;
    }
    public int getQuestionFontSize() {
        return this.qFontSize;
    }
    public int getOptionFontSize() {
        return this.optFontSize;
    }
    public int getBtnGap() { return this.btnGap; }
    public String getQuestion() {
        return this.question;
    }
    public int getAnalysisOpt() { return this.analysisOpt; }
    public String getAnalysis() { return this.analysis; }
    public Integer getImageResId() {
        return this.imgResId;
    }
    public String getOption1() {
        return this.option1;
    }
    public String getOption2() {
        return this.option2;
    }
    public String getOption3() {
        return this.option3;
    }
    public String getOption4() {
        return this.option4;
    }
}
