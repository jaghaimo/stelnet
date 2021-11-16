package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.Query;
import uilib.Button;
import uilib.RenderableComponent;
import uilib.property.Size;

public class ControlButtons extends RenderableComponent {

    private final Query query;
    private final Size buttonSize;

    private final float BUTTON_HEIGHT = 20;
    private final float PADDING = 8;

    public ControlButtons(float width, Query query) {
        this.query = query;
        this.buttonSize = new Size(0, BUTTON_HEIGHT);
        setSize(new Size(width, BUTTON_HEIGHT));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        Button preview = new Button(buttonSize, "Preview", true, Misc.getGrayColor());
        preview.setCutStyle(CutStyle.BOTTOM);
        Button off = new Button(buttonSize, "Off", true);
        off.setCutStyle(CutStyle.BOTTOM);
        Button delete = new Button(buttonSize, "Delete", true, Misc.getNegativeHighlightColor());
        delete.setCutStyle(CutStyle.BOTTOM);

        tooltip.setButtonFontVictor10();

        delete.render(tooltip);
        UIComponentAPI deleteButton = tooltip.getPrev();
        deleteButton.getPosition().setXAlignOffset(getSize().getWidth() - delete.getSize().getWidth());
        deleteButton.getPosition().setYAlignOffset(-1);

        off.render(tooltip);
        UIComponentAPI offButton = tooltip.getPrev();
        offButton.getPosition().leftOfTop(deleteButton, 1);

        preview.render(tooltip);
        UIComponentAPI previewButton = tooltip.getPrev();
        previewButton.getPosition().leftOfTop(offButton, 1);

        tooltip.setButtonFontDefault();
    }
}
