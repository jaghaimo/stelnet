package stelnet.board.commodity2;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.util.L10n;
import uilib2.Drawable;
import uilib2.Layout;
import uilib2.Rectangle;
import uilib2.UiConstants;
import uilib2.intel.DrawableIntelInfo;
import uilib2.intel.SimpleDrawableInfo;
import uilib2.label.HighlightFirst;
import uilib2.layouts.BasicLayout;
import uilib2.layouts.CompositeLayout;
import uilib2.layouts.FixedSizeLayout;
import uilib2.layouts.RelocatedLayout;

@RequiredArgsConstructor
public class CommodityView {

    private final CommodityModel model;
    private final int filterWidth = 200;

    public DrawableIntelInfo getIntelInfo() {
        final int contactNumber = model.getContactNumber();
        return new SimpleDrawableInfo(
            L10n.contacts("BOARD_TITLE"),
            L10n.contacts("BOARD_DESCRIPTION", contactNumber),
            null,
            new HighlightFirst(String.valueOf(contactNumber))
        );
    }

    public Layout getLayout(final float width, final float height) {
        final CompositeLayout layout = new CompositeLayout();
        layout.getLayouts().add(getContactLayout(width - UiConstants.SPACER_SMALL, height));
        layout.getLayouts().add(getFilterLayout(width, height));
        return layout;
    }

    private Layout getContactLayout(final float width, final float height) {
        final List<Drawable> drawables = new ArrayList<Drawable>(model.getMarkets());
        return new FixedSizeLayout(new BasicLayout(drawables), width - filterWidth, height);
    }

    private Layout getFilterLayout(final float width, final float height) {
        final List<Drawable> drawables = new ArrayList<>();
        drawables.add(new Rectangle(filterWidth, height));
        return new RelocatedLayout(
            new FixedSizeLayout(new BasicLayout(drawables), filterWidth, height),
            width - filterWidth,
            0
        );
    }
}
