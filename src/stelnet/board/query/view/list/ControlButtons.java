package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.Query;
import uilib.Button;
import uilib.C2Button;
import uilib.RenderableComponent;
import uilib.property.Size;

public class ControlButtons extends RenderableComponent {

    private final Query query;
    private final Size buttonSize;

    private final float BUTTON_HEIGHT = 20;
    private final float PADDING = 8;

    public ControlButtons(float width, Query query) {
        this.query = query;
        this.buttonSize = new Size(width, BUTTON_HEIGHT);
        setSize(new Size(width, BUTTON_HEIGHT + PADDING + BUTTON_HEIGHT));
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        Button off = new C2Button(buttonSize, "Off", true);
        off.setSize(buttonSize);
        Button delete = new C2Button(buttonSize, "Delete", true, Misc.getNegativeHighlightColor());
        delete.setSize(buttonSize);

        tooltip.setButtonFontVictor10();
        tooltip.addSpacer(2 + PADDING / 2);

        off.render(tooltip);
        UIComponentAPI offButton = tooltip.getPrev();
        delete.render(tooltip);
        UIComponentAPI deleteButton = tooltip.getPrev();

        deleteButton.getPosition().belowMid(offButton, 2 + PADDING / 2);
    }
}
