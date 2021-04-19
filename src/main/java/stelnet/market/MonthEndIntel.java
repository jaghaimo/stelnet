package stelnet.market;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.SettingHelper;

public class MonthEndIntel extends BaseIntelPlugin {

    private String message;

    public MonthEndIntel(String message) {
        this.message = message;
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        info.addPara("Market Search", getTitleColor(mode), 0f);
        indent(info);
        info.addPara(message, 3f, getBulletColorForMode(mode));
        unindent(info);
    }

    @Override
    public String getIcon() {
        return SettingHelper.getSpriteName("market");
    }
}
