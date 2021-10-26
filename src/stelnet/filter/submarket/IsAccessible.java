package stelnet.filter.submarket;

import com.fs.starfarer.api.campaign.CampaignUIAPI.CoreUITradeMode;
import com.fs.starfarer.api.campaign.CoreUIAPI;
import com.fs.starfarer.api.campaign.SubmarketPlugin;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.HintPanelAPI;

public class IsAccessible implements SubmarketFilter {

    public boolean accept(SubmarketAPI submarket) {
        DummyCoreUi openTrade = new DummyCoreUi(CoreUITradeMode.OPEN);
        DummyCoreUi sneakTrade = new DummyCoreUi(CoreUITradeMode.SNEAK);
        SubmarketPlugin plugin = submarket.getPlugin();
        return plugin.isEnabled(openTrade) || plugin.isEnabled(sneakTrade);
    }

    private static class DummyCoreUi implements CoreUIAPI {

        private final CoreUITradeMode tradeMode;

        public DummyCoreUi(CoreUITradeMode tradeMode) {
            this.tradeMode = tradeMode;
        }

        @Override
        public HintPanelAPI getHintPanel() {
            return null;
        }

        @Override
        public CoreUITradeMode getTradeMode() {
            return tradeMode;
        }
    }
}
