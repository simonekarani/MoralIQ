//
//  BusinessEthicsModel.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.bizethics;

public class BusinessEthicsModel {
    private int questionFontSize;
    private int optFontSize;
    private int btnGap;
    private String question;
    private int analysisOpt;
    private String analysis;
    private Integer imageResId;
    private int buttonCount;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public BusinessEthicsModel(String question, int qFont, int resId, int optFont, int btnGap, int analysisOpt, String analysis, String opt1, String opt2, String opt3) {
        this.question = question;
        this.questionFontSize = qFont;
        this.imageResId = resId;
        this.buttonCount = 3;
        this.optFontSize = optFont;
        this.btnGap = btnGap;
        this.analysisOpt = analysisOpt;
        this.analysis = analysis;
        this.option1 = opt1;
        this.option2 = opt2;
        this.option3 = opt3;
    }

    public BusinessEthicsModel(String question, int qFont, int resId, int optFont, int btnGap, int analysisOpt, String analysis, String opt1, String opt2, String opt3, String opt4) {
        this.question = question;
        this.questionFontSize = qFont;
        this.imageResId = resId;
        this.buttonCount = 3;
        this.optFontSize = optFont;
        this.btnGap = btnGap;
        this.analysisOpt = analysisOpt;
        this.analysis = analysis;
        this.option1 = opt1;
        this.option2 = opt2;
        this.option3 = opt3;
        this.option4 = opt4;
    }
    public int getQuestionFontSize() {
        return this.questionFontSize;
    }
    public int getOptionFontSize() {
        return this.optFontSize;
    }
    public int getBtnGap() { return this.btnGap; }
    public int getAnalysisOpt() { return this.analysisOpt; }
    public String getAnalysis() { return this.analysis; }
    public String getQuestion() {
        return this.question;
    }
    public Integer getImageResId() {
        return this.imageResId;
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
