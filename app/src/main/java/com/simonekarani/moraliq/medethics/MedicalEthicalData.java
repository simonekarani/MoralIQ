package com.simonekarani.moraliq.medethics;

import com.simonekarani.moraliq.R;

public class MedicalEthicalData {
    public static String MED_ETHICAL_1  = "Would you ever recommend or give life-sustaining therapy when you judged it was futile?";
    public static String MED_ETHICAL_2  = "Would you ever consider halting life-sustaining therapy because of family demands, even if you felt that it was premature?";
    public static String MED_ETHICAL_3  = "Would you ever prescribe a treatment that was a placebo simply because the patient wanted treatment?";
    public static String MED_ETHICAL_4  = "Would you ever undertreat a patient's pain for the fear of the patient becoming addicted?";
    public static String MED_ETHICAL_5  = "Would you ever hide information from a patient about a terminal or preterminal diagnosis in an effort to bolster their spirit or attitude?";
    public static String MED_ETHICAL_6  = "Are there times when it's acceptable to cover up or avoid revealing a mistake if that mistake would not cause harm to the patient?";
    public static String MED_ETHICAL_7  = "Are there times when it's acceptable to cover up or avoid revealing a mistake if that mistake would potentially or likely harm the patient?";
    public static String MED_ETHICAL_8  = "Is it acceptable to perform a procedure that isn't medically warranted, for reasons of defensive medicine?";
    public static String MED_ETHICAL_9  = "Should physician-assisted suicide be allowed in some situations?";
    public static String MED_ETHICAL_10 = "If a physician friend or colleague were impaired (alcohol, drugs, or illness) or was no longer competent, and he or she ignored your warnings to get help, would you report that person to a superior or at the medical board?";
    public static String MED_ETHICAL_11 = "Would you ever refer a patient to a physician simply to return a favor to the doctor, when you didn't think that person was the best qualified?";
    public static String MED_ETHICAL_12 = "Would you feel conflicted about dropping insurers that don't pay well, even though some longtime patients would probably stop seeing you?";
    public static String MED_ETHICAL_13 = "Is it ever acceptable to overstate or falsify a patient's condition when submitting claims or seeking prior authorization?";
    public static String MED_ETHICAL_14 = "Would you ever \"fire\" a \"non-compliant\" patient so that you could improve your pay-for-performance (P4P) scores or to stop dealing with patients who \"overuse\" resources on their capitation plans?";
    public static String MED_ETHICAL_15 = "Would you ever discuss patient information in situations that did not fully protect their privacy, eg, socially or while talking with doctors about subjects not related to the patient?";
    public static String MED_ETHICAL_16 = "Is it ever acceptable to break patient confidentiality if you know that a patient's health status may be harming others?";
    public static String MED_ETHICAL_17 = "Would you agree that you should refuse gifts or perks from pharmaceutical companies because they may influence your medical judgment?";
    public static String MED_ETHICAL_18 = "Could you become involved in a romantic or sexual relationship with a patient?";
    public static String MED_ETHICAL_19 = "Should it be legal for people to buy organs for transplant, if they would not be able to receive an organ by waiting their turn through the national database?";
    public static String MED_ETHICAL_20 = "Would you perform an abortion in certain situations, even if it were against your beliefs?";
    public static String MED_ETHICAL_21 = "Would you ever devote scarce or costly resources to a younger patient rather than to one who was older but not facing imminent death?";
    public static String MED_ETHICAL_22 = "Is it ever acceptable to perform 'unnecessary' procedures due to malpractice concerns?";
    public static String MED_ETHICAL_23 = "Would you ever continue treating a patient-despite a family's wishes to end treatment-if you felt the patient had a chance to recover?";
    public static String MED_ETHICAL_24 = "Is it right to provide intensive care to a newborn who will either die soon or survive but have an objectively terrible quality of life?";
    public static String MED_ETHICAL_25 = "Do you ever feel conflicted about reporting or investigating your suspicions that a patient was a victim of domestic abuse?";

    public static MedicalEthicalModel MedEthicalData1  = new MedicalEthicalModel(MED_ETHICAL_1,  24, R.drawable.med_ethic1,  24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData2  = new MedicalEthicalModel(MED_ETHICAL_2,  24, R.drawable.med_ethic2,  24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData3  = new MedicalEthicalModel(MED_ETHICAL_3,  24, R.drawable.med_ethic3,  24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData4  = new MedicalEthicalModel(MED_ETHICAL_4,  24, R.drawable.med_ethic4,  24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData5  = new MedicalEthicalModel(MED_ETHICAL_5,  24, R.drawable.med_ethic5,  19, "Yes, I soften it and give hope even if there's little chance", "Yes, unless someone is going to die immediately, I don't tell them how bad it is", "No, I tell it exactly as I see it", "It Depends");
    public static MedicalEthicalModel MedEthicalData6  = new MedicalEthicalModel(MED_ETHICAL_6,  24, R.drawable.med_ethic6,  24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData7  = new MedicalEthicalModel(MED_ETHICAL_7,  23, R.drawable.med_ethic7,  24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData8  = new MedicalEthicalModel(MED_ETHICAL_8,  24, R.drawable.med_ethic8,  24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData9  = new MedicalEthicalModel(MED_ETHICAL_9,  24, R.drawable.med_ethic9,  24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData10 = new MedicalEthicalModel(MED_ETHICAL_10, 19, R.drawable.med_ethic10, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData11 = new MedicalEthicalModel(MED_ETHICAL_11, 24, R.drawable.med_ethic11, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData12 = new MedicalEthicalModel(MED_ETHICAL_12, 22, R.drawable.med_ethic12, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData13 = new MedicalEthicalModel(MED_ETHICAL_13, 24, R.drawable.med_ethic13, 20, "Yes, in order to get a patient the services, such as drugs, treatment, or hospital coverage", "Yes, in order to bring in more revenue", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData14 = new MedicalEthicalModel(MED_ETHICAL_14, 20, R.drawable.med_ethic14, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData15 = new MedicalEthicalModel(MED_ETHICAL_15, 20, R.drawable.med_ethic15, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData16 = new MedicalEthicalModel(MED_ETHICAL_16, 24, R.drawable.med_ethic16, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData17 = new MedicalEthicalModel(MED_ETHICAL_17, 22, R.drawable.med_ethic17, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData18 = new MedicalEthicalModel(MED_ETHICAL_18, 24, R.drawable.med_ethic18, 20, "Yes, while that patient is still a patient", "Yes, but not until at least 6 months after they stopped being a patient", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData19 = new MedicalEthicalModel(MED_ETHICAL_19, 21, R.drawable.med_ethic19, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData20 = new MedicalEthicalModel(MED_ETHICAL_20, 24, R.drawable.med_ethic20, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData21 = new MedicalEthicalModel(MED_ETHICAL_21, 22, R.drawable.med_ethic21, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData22 = new MedicalEthicalModel(MED_ETHICAL_22, 24, R.drawable.med_ethic22, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData23 = new MedicalEthicalModel(MED_ETHICAL_23, 24, R.drawable.med_ethic23, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData24 = new MedicalEthicalModel(MED_ETHICAL_24, 24, R.drawable.med_ethic24, 24, "Yes", "No", "It Depends");
    public static MedicalEthicalModel MedEthicalData25 = new MedicalEthicalModel(MED_ETHICAL_25, 24, R.drawable.med_ethic25, 24, "Yes", "No", "It Depends");

    public static MedicalEthicalModel[] MED_ETHICAL_DATA_LIST = {
            MedEthicalData1,  MedEthicalData2,  MedEthicalData3,  MedEthicalData4,  MedEthicalData5,
            MedEthicalData6,  MedEthicalData7,  MedEthicalData8,  MedEthicalData9,  MedEthicalData10,
            MedEthicalData11, MedEthicalData12, MedEthicalData13, MedEthicalData14, MedEthicalData15,
            MedEthicalData16, MedEthicalData17, MedEthicalData18, MedEthicalData19, MedEthicalData20,
            MedEthicalData21, MedEthicalData22, MedEthicalData23, MedEthicalData24, MedEthicalData25
    };
}
