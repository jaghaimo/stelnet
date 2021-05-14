package stelnet.market;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.L10n;
import stelnet.helper.GlobalSettingsHelper;

public class MonthEndIntel extends BaseIntelPlugin {

    private final String message;

    public MonthEndIntel(String message) {
        this.message = L10n.get(message);
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        info.addPara(L10n.get("marketMonthEndTitle"), getTitleColor(mode), 0f);
        indent(info);
        info.addPara(message, 3f, getBulletColorForMode(mode));
        unindent(info);
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("market");
    }
}
