package stelnet.board.commodity2;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import lombok.Getter;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;

@Getter
public class NewCommodityBoard extends BaseIntelPlugin {

    private final String icon = StelnetHelper.getSpriteName("commodity");
    private final String intelTags = ModConstants.TAG_COMMODITY;

    @Override
    public boolean hasSmallDescription() {
        return false;
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }
}
