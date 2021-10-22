package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import uilib.property.Location;
import uilib.property.Position;
import uilib.property.Size;

/**
 * Basic rendering logic for components {@link CustomPanelAPI}. Elements still
 * need to implement their own rendering logic using {@link TooltipMakerAPI}).
 *
 * Can account for {@link Location} (TOP_LEFT by default) and {@link Position}
 * offset (0, 0 by default). Comes with a scroller (by default) if needs to use
 * larger screen than available in {@link CustomPanelAPI} panel.
 */
@Getter
@Setter
@Log4j
public abstract class AbstractRenderable implements Renderable {

    private Location location = Location.TOP_LEFT;
    private Position offset = new Position(0, 0);
    private boolean withScroller = true;
    private Size size;

    protected void log(Position position) {
        log.debug(
            String.format("Rendering %s in %s at %s", this, position, location)
        );
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        log(new Position(x, y));
        Size size = getSize();
        TooltipMakerAPI inner = panel.createUIElement(
            size.getWidth(),
            size.getHeight(),
            withScroller
        );
        render(inner);
        PositionAPI element = panel.addUIElement(inner);
        location.render(element, x + offset.getX(), y + offset.getY());
    }

    /**
     * Guestimate size of the string.
     *
     * @link https://stackoverflow.com/a/60643245
     */
    protected float getTextWidth(String s) {
        String lookup =
            " .:,;'^`!|jl/\\i-()JfIt[]?{}sr*a\"ce_gFzxk+0123456789<=>~qvy$opnSbduEhTBXY#VRKZN%GUAHD@OQ&wmWLPCM";
        int result = 0;
        for (int i = 0; i < s.length(); ++i) {
            int c = lookup.indexOf(s.charAt(i));
            result += (c < 0 ? 60 : c) * 9 + 200;
        }
        return (float) result / 40;
    }
}
