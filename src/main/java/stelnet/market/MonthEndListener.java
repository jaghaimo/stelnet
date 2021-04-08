package stelnet.market;

import java.util.List;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelManagerAPI;
import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

public class MonthEndListener implements EconomyTickListener {

    public static void register() {
        ListenerManagerAPI listenerManager = Global.getSector().getListenerManager();
        List<MonthEndListener> listeners = listenerManager.getListeners(MonthEndListener.class);
        if (listeners.isEmpty()) {
            MonthEndListener listener = new MonthEndListener();
            listenerManager.addListener(listener);
        }
    }

    @Override
    public void reportEconomyMonthEnd() {
        MonthEndIntel intel = new MonthEndIntel(
                "New month has started. Information intel could be stale - please consider refreshing.");
        toggleIntel(intel);
    }

    @Override
    public void reportEconomyTick(int tick) {
        int maxTicks = Global.getSettings().getInt("economyIterPerMonth");
        boolean isSecondToLastTick = maxTicks - tick == 2;
        if (isSecondToLastTick) {
            MonthEndIntel intel = new MonthEndIntel(
                    "New month will start soon and Information intel may become stale!");
            toggleIntel(intel);
        }
    }

    private void toggleIntel(BaseIntelPlugin intel) {
        IntelManagerAPI intelManager = Global.getSector().getIntelManager();
        intelManager.addIntel(intel);
        intelManager.removeIntel(intel);
    }
}
