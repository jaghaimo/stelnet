package stelnet.board.commodity2;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import uilib2.Layout;
import uilib2.intel.DrawableIntelInfo;
import uilib2.intel.LargeIntel;

@Getter
public class CommodityBoard extends LargeIntel {

    private final transient String icon = StelnetHelper.getSpriteName("commodity");
    private final transient String mainTag = ModConstants.TAG_COMMODITY;
    private final transient IntelSortTier sortTier = IntelSortTier.TIER_0;
    private final transient CommodityModel model = new CommodityModel(this);
    private final transient CommodityView view = new CommodityView(model);

    public Object readResolve() {
        return new CommodityBoard();
    }

    @Override
    public void notifyPlayerAboutToOpenIntelScreen() {
        model.update(getAllMarkets());
    }

    @Override
    protected DrawableIntelInfo getIntelInfo() {
        return view.getIntelInfo();
    }

    @Override
    protected Layout getLayout(final float width, final float height) {
        return view.getLayout(width, height);
    }

    private List<MarketAPI> getAllMarkets() {
        return Collections.emptyList();
    }
}
