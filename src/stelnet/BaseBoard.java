package stelnet;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.comm.IntelInfoPlugin;
import com.fs.starfarer.api.ui.SectorMapAPI;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import uilib.Renderable;
import uilib.RenderableIntel;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Log4j
public abstract class BaseBoard extends RenderableIntel {

    private final IntelSortTier sortTier = IntelSortTier.TIER_0;

    /**
     * Singleton, creates or gets an existing instance of a class that implements IntelInfoPlugin.
     * Requires no-args constructor. Used by all "Boards".
     */
    public static <T extends IntelInfoPlugin> T getInstance(Class<T> className) {
        IntelInfoPlugin intel = Global.getSector().getIntelManager().getFirstIntel(className);
        if (intel == null) {
            try {
                @SuppressWarnings("deprecation")
                IntelInfoPlugin board = className.newInstance();
                Global.getSector().getIntelManager().addIntel(board, true);
                intel = board;
            } catch (Exception exception) {
                log.error("Couldn't create board for " + className.getName(), exception);
            }
        }
        return className.cast(intel);
    }

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
