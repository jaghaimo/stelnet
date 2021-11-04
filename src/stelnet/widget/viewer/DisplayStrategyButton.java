package stelnet.widget.viewer;

import com.fs.starfarer.api.util.Misc;
import org.lwjgl.input.Keyboard;
import stelnet.util.L10n;
import uilib.Button;
import uilib.property.Location;
import uilib.property.Size;

public class DisplayStrategyButton extends Button {

    public DisplayStrategyButton(final GroupingStrategy newStrategy) {
        super(new Size(180, 24), L10n.get("viewStrategy" + newStrategy.id), true, Misc.getButtonTextColor());
        setShortcut(Keyboard.KEY_G);
        setLocation(Location.BOTTOM_RIGHT);
        // setOffset(new Position(16, 8));
    }
}
