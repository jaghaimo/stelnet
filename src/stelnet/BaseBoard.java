package stelnet;

import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.List;
import java.util.Set;
import uilib.Renderable;
import uilib.RenderableIntel;
import uilib.RenderableState;
import uilib.property.Size;

public abstract class BaseBoard extends RenderableIntel {

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
