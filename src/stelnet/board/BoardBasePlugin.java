package stelnet.board;

import com.fs.starfarer.api.ui.SectorMapAPI;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import uilib.Renderable;
import uilib.RenderableIntel;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
public abstract class BoardBasePlugin extends RenderableIntel {

    private final IntelSortTier sortTier = IntelSortTier.TIER_0;

    @Override
    public Set<String> getIntelTags(SectorMapAPI map) {
        Set<String> tags = super.getIntelTags(map);
        tags.add(getTag());
        return tags;
    }

    @Override
    public boolean hasLargeDescription() {
        return true;
    }

    @Override
    protected List<Renderable> getRenderableList(Size size) {
        return getRenderableState().toRenderableList(size);
    }

    protected abstract RenderableState getRenderableState();

    protected abstract String getTag();
}
