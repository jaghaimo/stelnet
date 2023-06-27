package stelnet.board.contact2;

import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.ui.Alignment;
import lombok.Getter;
import stelnet.board.BoardDrawableInfo;
import stelnet.board.contact.ContactsState;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib2.Layout;
import uilib2.Spacer;
import uilib2.UiConstants;
import uilib2.intel.DrawableIntelInfo;
import uilib2.intel.LargeIntel;
import uilib2.label.ParaBasic;
import uilib2.label.SectionHeadingBasic;
import uilib2.layouts.BasicLayout;
import uilib2.layouts.CompositeLayout;
import uilib2.layouts.FixedSizeLayout;
import uilib2.layouts.RelocatedLayout;

@Getter
public class NewContactsBoard extends LargeIntel {

    private static final float filtersWidth = 300;
    private final String icon = StelnetHelper.getSpriteName("contacts");
    private final String mainTag = Tags.INTEL_CONTACTS;
    private final ContactsState renderableState = new ContactsState();

    private final IntelSortTier sortTier = IntelSortTier.TIER_0;

    @Override
    public boolean isHidden() {
        return renderableState.getContactNumber() == 0;
    }

    @Override
    protected Layout getLayout(final float width, final float height) {
        final CompositeLayout layout = new CompositeLayout();
        layout.add(getContactsLayout(width, height));
        layout.add(getFiltersLayout(width, height));
        return layout;
    }

    @Override
    protected DrawableIntelInfo getIntelInfo() {
        return new BoardDrawableInfo(
            L10n.contacts("BOARD_TITLE"),
            L10n.contacts("BOARD_DESCRIPTION", renderableState.getContactNumber())
        );
    }

    private Layout getContactsLayout(final float width, final float height) {
        final BasicLayout layout = new BasicLayout();
        for (int i = 0; i < 10; i++) {
            layout.add(new SectionHeadingBasic("Hello Contacts", Alignment.MID, 0));
            layout.add(new Spacer(UiConstants.SPACER_DEFAULT));
            layout.add(new ParaBasic("Some text here.", 0));
            layout.add(new Spacer(UiConstants.SPACER_LARGE));
        }
        return new FixedSizeLayout(layout, width - filtersWidth - UiConstants.SPACER_DEFAULT, height);
    }

    private Layout getFiltersLayout(final float width, final float height) {
        final BasicLayout layout = new BasicLayout();
        layout.add(new SectionHeadingBasic("Hello Filters", Alignment.MID, 0));
        for (int i = 0; i < 10; i++) {
            layout.add(new ParaBasic("Filters", 0));
        }
        return new RelocatedLayout(new FixedSizeLayout(layout, filtersWidth, height), width - filtersWidth, 0);
    }
}
