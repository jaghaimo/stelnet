package stelnet.helper;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;

public class GlobalHelper {

    public static EconomyAPI getEconomy() {
        return Global.getSector().getEconomy();
    }
}
