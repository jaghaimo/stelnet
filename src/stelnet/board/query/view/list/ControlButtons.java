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
        preview.setCutStyle(CutStyle.TOP);
        Button onOff = new OnOffButton(buttonSize, query);
        Button delete = new DeleteButton(buttonSize, query);

        tooltip.setButtonFontVictor10();

        delete.render(tooltip);
        UIComponentAPI deleteComponent = tooltip.getPrev();
        deleteComponent.getPosition().setXAlignOffset(getSize().getWidth() - delete.getSize().getWidth());
        deleteComponent.getPosition().setYAlignOffset(-PADDING);

        onOff.render(tooltip);
        UIComponentAPI onOffComponent = tooltip.getPrev();
        onOffComponent.getPosition().leftOfTop(deleteComponent, 1);

        preview.render(tooltip);
        UIComponentAPI previewComponent = tooltip.getPrev();
        previewComponent.getPosition().leftOfTop(onOffComponent, 1);

        tooltip.setButtonFontDefault();
    }
}
