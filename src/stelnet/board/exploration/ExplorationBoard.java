package stelnet.board.exploration;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import stelnet.filter.Filter;
import stelnet.filter.IsObject;
import stelnet.filter.LogicalNot;
import stelnet.util.StelnetHelper;
import uilib2.Drawable;
import uilib2.intel.DrawableIntelInfo;
import uilib2.intel.SmallIntel;

@Getter
public class ExplorationBoard extends SmallIntel {

    private transient String icon = StelnetHelper.getSpriteName("exploration");
    private transient String mainTag = Tags.INTEL_EXPLORATION;
    private transient IntelSortTier sortTier = IntelSortTier.TIER_0;
    private transient Filter excludeBoardFilter = new LogicalNot(new IsObject(this));
    private transient ExplorationModel model = new ExplorationModel(this, excludeBoardFilter);
    private transient ExplorationView view = new ExplorationView(model);

    public Object readResolve() {
        return new ExplorationBoard();
    }

    @Override
    public void notifyPlayerAboutToOpenIntelScreen() {
        model.updateFactions(getAllFactions());
        model.updateIntelList(getAllIntel());
        model.changeIntelVisibility();
    }

    @Override
    protected DrawableIntelInfo getIntelInfo() {
        return view.getIntelInfo();
    }

    @Override
    protected List<Drawable> getDrawableList(final float width, final float height) {
        model.changeIntelVisibility();
        return view.getDrawableList(width);
    }

    private List<FactionAPI> getAllFactions() {
        return Global.getSector().getAllFactions();
    }

    private List<IntelInfoPlugin> getAllIntel() {
        return new ArrayList<>(Global.getSector().getIntelManager().getIntel());
    }
}
