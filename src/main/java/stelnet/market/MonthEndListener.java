package stelnet.market;

import java.util.List;

import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

import stelnet.helper.GlobalHelper;
import stelnet.helper.IntelHelper;

public class MonthEndListener implements EconomyTickListener {

    public static void register() {
        ListenerManagerAPI listenerManager = GlobalHelper.getListenerManager();
        List<MonthEndListener> listeners = listenerManager.getListeners(MonthEndListener.class);
        if (listeners.isEmpty()) {
            MonthEndListener listener = new MonthEndListener();
            listenerManager.addListener(listener);
        }
    }

    @Override
    public void reportEconomyMonthEnd() {
        MonthEndIntel intel = new MonthEndIntel(
                "New month has started. Market intel could be stale - please consider refreshing.");
        toggleIntel(intel);
    }

    @Override
    public void reportEconomyTick(int tick) {
        int maxTicks = GlobalHelper.getEconomyIterPerMonth();
        boolean isSecondToLastTick = maxTicks - tick == 2;
        if (isSecondToLastTick) {
            MonthEndIntel intel = new MonthEndIntel("New month will start soon and Market intel may become stale!");
            toggleIntel(intel);
        }
    }

    private void toggleIntel(BaseIntelPlugin intel) {
        IntelHelper.addIntel(intel);
        IntelHelper.removeIntel(intel);
    }
}
