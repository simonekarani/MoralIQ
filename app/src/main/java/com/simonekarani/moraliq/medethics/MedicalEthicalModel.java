package com.simonekarani.moraliq.medethics;

public class MedicalEthicalModel {
    private String question;
    private int questionFontSize;
    private int imageResId;
    private int buttonCount;
    private int optFontSize;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public MedicalEthicalModel(String question, int qFont, int resId, int optFont, String opt1, String opt2, String opt3) {
        this.question = question;
        this.questionFontSize = qFont;
        this.imageResId = resId;
        this.buttonCount = 3;
        this.optFontSize = optFont;
        this.option1 = opt1;
        this.option2 = opt2;
        this.option3 = opt3;
    }

    public MedicalEthicalModel(String question, int qFont, int resId, int optFont, String opt1, String opt2, String opt3, String opt4) {
        this.question = question;
        this.questionFontSize = qFont;
        this.imageResId = resId;
        this.buttonCount = 4;
        this.optFontSize = optFont;
        this.option1 = opt1;
        this.option2 = opt2;
        this.option3 = opt3;
        this.option4 = opt4;
    }

    public String getQuestion() { return this.question; }
    public int getQuestionFontSize() { return this.questionFontSize; }
    public int getImageResId() { return this.imageResId; }
    public int getButtonCount() { return this.buttonCount; }
    public int getOptionFontSize() { return this.optFontSize; }
    public String getOption1() { return this.option1; }
    public String getOption2() { return this.option2; }
    public String getOption3() { return this.option3; }
    public String getOption4() { return this.option4; }
}
