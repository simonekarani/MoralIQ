//
//  MainScreenData.java
//  EthicalIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 EthicalIQ. All rights reserved.
//

package com.simonekarani.moraliq.model;

import com.simonekarani.moraliq.R;

public class MainScreenData {
    public static final int MORAL_DILEMMA_ID        = 0;
    public static final int MORAL_MEDICAL_ETHICS_ID = 1;
    public static final int MORAL_TECH_ETHICS_ID    = 2;
    public static final int MORAL_BIZ_ETHICS_ID     = 3;
    public static final int MORAL_SELF_DRIVING_ID   = 4;

    public static String[] nameArray = {
            "Moral Dilemma", "Medical Ethics", "Tech Ethics", "Business Ethics", "Moral Machine"
    };

    public static Integer[] drawableArray = {
            R.drawable.dilemma, R.drawable.medethics, R.drawable.techethics, R.drawable.bizethics, R.drawable.driving
    };

    public static Integer[] id_ = {0, 1, 2, 3, 4};
}
