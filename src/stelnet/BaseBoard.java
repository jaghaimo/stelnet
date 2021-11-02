package stelnet;

import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.List;
import java.util.Set;
import lombok.extern.log4j.Log4j;
import stelnet.util.IntelManager;
import uilib.Renderable;
import uilib.RenderableIntel;
import uilib.RenderableState;
import uilib.property.Size;

@Log4j
public abstract class BaseBoard extends RenderableIntel {

    public static <T extends BaseIntelPlugin> T getInstance(Class<T> className) {
        IntelInfoPlugin intel = IntelManager.getFirstIntel(className);
        if (intel == null) {
            try {
                BaseIntelPlugin board = className.newInstance();
                IntelManager.addIntel(board, true);
                intel = board;
            } catch (Exception exception) {
                log.warn("Couldn't create board for " + className.getName(), exception);
            }
        }
        return className.cast(intel);
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        BoardInfo boardInfo = getBoardInfo();
        info.addPara(boardInfo.getTitle(), getTitleColor(mode), 0);
        info.addPara(boardInfo.getDescription(), getBulletColorForMode(mode), 1);
    }

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(getTag());
        return tags;
    }

    @Override
    public IntelSortTier getSortTier() {
        return IntelSortTier.TIER_0;
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    protected List<Renderable> getRenderables(Size size) {
        return getRenderableState().toRenderables(size);
    }

    protected abstract BoardInfo getBoardInfo();

    protected abstract RenderableState getRenderableState();

    protected abstract String getTag();
}
