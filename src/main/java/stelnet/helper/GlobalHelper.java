package stelnet.helper;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.impl.campaign.events.OfficerManagerEvent;

public class GlobalHelper {

    public static EconomyAPI getEconomy() {
        return Global.getSector().getEconomy();
    }

    public static OfficerManagerEvent getOfficerManagerEvent() {
        OfficerManagerEvent managerEvent;
        List<OfficerManagerEvent> listeners = Global.getSector().getListenerManager()
                .getListeners(OfficerManagerEvent.class);
        if (listeners.size() > 0) {
            managerEvent = listeners.get(0);
        } else {
            managerEvent = new OfficerManagerEvent();
        }
        return managerEvent;
    }
}
