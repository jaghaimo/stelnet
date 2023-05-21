package stelnet.board.exploration;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CoreUITabId;
import com.fs.starfarer.api.campaign.listeners.CoreUITabListener;
import lombok.extern.log4j.Log4j;

@Log4j
public class ExplorationListener implements CoreUITabListener {

    private static transient ExplorationListener instance;

    public static void register() {
        if (instance == null) {
            instance = new ExplorationListener();
        }
        Global.getSector().getListenerManager().addListener(instance, true);
        log.debug("Enabled Exploration Listener script");
    }

    public static void unregister() {
        if (instance != null) {
            Global.getSector().getListenerManager().removeListener(instance);
            instance = null;
            log.debug("Disabled Exploration Listener script");
        }
    }

    @Override
    public void reportAboutToOpenCoreTab(CoreUITabId tab, Object param) {
        if (tab != CoreUITabId.INTEL) {
            log.debug("Ignoring call due to non-intel UI being opened");
            return;
        }
        log.info("Forcing filters upon exploration tab intel");
        new ActionFilterIntel().act(null);
    }
}
