package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import uilib.Button;
import uilib.C2Button;
import uilib.RenderableComponent;
import uilib.property.Size;

@RequiredArgsConstructor
public class ControlButtons extends RenderableComponent {

    private final Query query;

    public ControlButtons(Size size, Query query) {
        this(query);
        setSize(size);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        Button off = new C2Button(new Size(100, 20), "Off", true);
        Button delete = new C2Button(new Size(100, 20), "Delete", true, Misc.getNegativeHighlightColor());

        tooltip.setButtonFontVictor10();

        off.render(tooltip);
        UIComponentAPI offButton = tooltip.getPrev();
        delete.render(tooltip);
        UIComponentAPI deleteButton = tooltip.getPrev();

        deleteButton.getPosition().belowMid(offButton, 10);
    }
}
