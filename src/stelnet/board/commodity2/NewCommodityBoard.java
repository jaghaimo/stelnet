package stelnet.board.commodity2;

import java.util.Collections;
import lombok.Getter;
import stelnet.board.BoardDrawableInfo;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import uilib2.Drawable;
import uilib2.Layout;
import uilib2.intel.DrawableIntelInfo;
import uilib2.intel.LargeIntel;
import uilib2.layouts.BasicLayout;

@Getter
public class NewCommodityBoard extends LargeIntel {

    private final String icon = StelnetHelper.getSpriteName("commodity");
    private final String mainTag = ModConstants.TAG_COMMODITY;

    @Override
    protected Layout getLayout() {
        return new BasicLayout(Collections.<Drawable>emptyList());
    }

    @Override
    protected DrawableIntelInfo getIntelInfo() {
        return new BoardDrawableInfo("Test", "Some desc...");
    }
}
