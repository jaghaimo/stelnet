package stelnet.board.exploration;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.ui.SectorMapAPI;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import stelnet.board.BoardDrawableInfo;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib2.Drawable;
import uilib2.intel.DrawableIntel;
import uilib2.intel.DrawableIntelInfo;

@Getter
public class ExplorationBoard extends DrawableIntel {

    private static ExplorationBoard instance;

    private final String icon = StelnetHelper.getSpriteName("exploration");
    private final DrawableIntelInfo intelInfo = new BoardDrawableInfo(
        L10n.get(ExplorationL10n.BOARD_TITLE),
        L10n.get(ExplorationL10n.BOARD_DESCRIPTION)
    );
    private final IntelSortTier sortTier = IntelSortTier.TIER_0;
    private final ExplorationState state = new ExplorationState(this);

    public static ExplorationBoard getInstance() {
        if (instance != null) {
            Global.getSector().getIntelManager().removeIntel(instance);
            instance = null;
        }
        if (instance == null) {
            instance = new ExplorationBoard();
            Global.getSector().getIntelManager().addIntel(instance, true);
        }
        return instance;
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(Tags.INTEL_EXPLORATION);
        return tags;
    }

    @Override
    protected List<Drawable> getDrawableList(float width, float height) {
        return state.toDrawableList(width, height);
    }
}
