package stelnet.market;

import java.util.List;

import com.fs.starfarer.api.campaign.listeners.EconomyTickListener;
import com.fs.starfarer.api.campaign.listeners.ListenerManagerAPI;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;

import stelnet.L10n;
import stelnet.helper.GlobalSectorHelper;
import stelnet.helper.GlobalSettingsHelper;
import stelnet.helper.IntelHelper;

public class MonthEndListener implements EconomyTickListener {

    public static void register() {
        ListenerManagerAPI listenerManager = GlobalSectorHelper.getListenerManager();
        List<MonthEndListener> listeners = listenerManager.getListeners(MonthEndListener.class);
        if (listeners.isEmpty()) {
            MonthEndListener listener = new MonthEndListener();
            listenerManager.addListener(listener);
        }
    }

    @Override
    public void reportEconomyMonthEnd() {
        MonthEndIntel intel = new MonthEndIntel(getTitle(), L10n.get("marketMonthNewMonth"));
        toggleIntel(intel);
    }

    @Override
    public void reportEconomyTick(int tick) {
        int maxTicks = GlobalSettingsHelper.getEconomyIterPerMonth();
        boolean isSecondToLastTick = maxTicks - tick == 2;
        if (isSecondToLastTick) {
            MonthEndIntel intel = new MonthEndIntel(getTitle(), L10n.get("marketMonthAlmostNew"));
            toggleIntel(intel);
        }
    }

    private void toggleIntel(BaseIntelPlugin intel) {
        IntelHelper.addIntel(intel);
        IntelHelper.removeIntel(intel);
    }

    private String getTitle() {
        return L10n.get("marketMonthEndTitle");
    }
}
