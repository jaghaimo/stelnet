package stelnet.market;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.RequiredArgsConstructor;
import stelnet.helper.GlobalSettingsHelper;

@RequiredArgsConstructor
public class MonthEndIntel extends BaseIntelPlugin {

    private final String title;
    private final String message;

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        info.addPara(title, getTitleColor(mode), 0f);
        indent(info);
        info.addPara(message, 3f, getBulletColorForMode(mode));
        unindent(info);
    }

    @Override
    public String getIcon() {
        return GlobalSettingsHelper.getSpriteName("market");
    }
}
