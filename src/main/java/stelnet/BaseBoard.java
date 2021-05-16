package stelnet;

import java.util.Set;

import com.fs.starfarer.api.ui.SectorMapAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.ui.RenderableIntel;

public abstract class BaseBoard extends RenderableIntel {

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        BoardInfo boardInfo = getBoardInfo();
        info.addPara(boardInfo.getTitle(), getTitleColor(mode), 0);
        info.addPara(boardInfo.getDescription(), getBulletColorForMode(mode), 1);
        info.addPara("", 0);
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

    protected abstract BoardInfo getBoardInfo();

    protected abstract String getTag();
}
