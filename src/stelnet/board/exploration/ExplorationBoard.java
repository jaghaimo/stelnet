package stelnet.board.exploration;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import java.util.List;
import lombok.Getter;
import stelnet.board.BoardRenderableInfo;
import stelnet.board.IntelBasePlugin;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;
import uilib.Renderable;
import uilib.property.Size;

@Getter
public class ExplorationBoard extends IntelBasePlugin {

    private static ExplorationBoard instance;

    private final String icon = StelnetHelper.getSpriteName("exploration");
    private final BoardRenderableInfo intelInfo = new BoardRenderableInfo(
        L10n.get(ExplorationL10n.BOARD_TITLE),
        L10n.get(ExplorationL10n.BOARD_DESCRIPTION)
    );
    private final ExplorationState state = new ExplorationState();
    private final String tag = Tags.INTEL_EXPLORATION;

    public static ExplorationBoard getInstance() {
        if (instance == null) {
            instance = new ExplorationBoard();
            Global.getSector().getIntelManager().addIntel(instance, true);
        }
        return instance;
    }

    public ExplorationBoard() {
        super(null, null);
        this.setSortTier(IntelSortTier.TIER_0);
    }

    @Override
    protected List<Renderable> getRenderableList(Size size) {
        return this.state.toRenderableList(size);
    }
}
