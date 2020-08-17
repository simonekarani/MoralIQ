//
//  BusinessEthicsData.java
//  MoralIQ
//
//  Created by Simone Karani on 2/9/20.
//  Copyright © 2020 MoralIQ. All rights reserved.
//

package com.simonekarani.moraliq.bizethics;

import com.simonekarani.moraliq.R;
import com.simonekarani.moraliq.bizethics.BusinessEthicsModel;

public class BusinessEthicsData {
    public static final BusinessEthicsModel BEthics1  =  new BusinessEthicsModel("\n\nShould we edit our children's genes?",20, R.drawable.techthics_1,20, 30, "Yes", "No", "Maybe, to eliminate genetic related diseases", "Yes, to support generation of tissues and whole organs to treat patients");

    public static final BusinessEthicsModel TechEthicsDataList[];

    static {
        TechEthicsDataList = new BusinessEthicsModel[]{
                BEthics1
        };
    }
}
