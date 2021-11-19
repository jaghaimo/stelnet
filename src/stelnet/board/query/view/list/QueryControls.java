package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.PositionAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import com.fs.starfarer.api.util.Misc;
import stelnet.board.query.Query;
import uilib.Heading;
import uilib.RenderableComponent;
import uilib.ToggleButton;
import uilib.UiConstants;
import uilib.property.Size;

public class QueryControls extends RenderableComponent {

    private final Query query;

    public QueryControls(float width, Query query) {
        this.query = query;
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.setButtonFontVictor10();
        renderHeading(tooltip);
        UIComponentAPI headingComponent = tooltip.getPrev();
        PositionAPI headingPosition = headingComponent.getPosition();
        headingPosition.setSize(headingPosition.getWidth() - 200, headingPosition.getHeight());

        UIComponentAPI deleteComponent = renderDelete(tooltip);
        UIComponentAPI onOffComponent = renderOnOff(tooltip, deleteComponent);
        renderPreview(tooltip, onOffComponent);
        tooltip.setButtonFontDefault();
    }

    private void renderHeading(TooltipMakerAPI tooltip) {
        tooltip.setParaFontVictor14();
        String headingText = String.format(" #%d", query.getNumber());
        Heading heading = new Heading(headingText, Misc.getBasePlayerColor(), Misc.getDarkPlayerColor());
        if (!query.isEnabled()) {
            heading.setForegroundColor(Misc.scaleAlpha(Misc.getBasePlayerColor(), 0.3f));
            heading.setBackgroundColor(Misc.scaleAlpha(Misc.getDarkPlayerColor(), 0.2f));
        }
        heading.setAlignment(Alignment.LMID);
        heading.render(tooltip);
    }

    private UIComponentAPI renderDelete(TooltipMakerAPI tooltip) {
        ToggleButton delete = new DeleteButton(query);
        delete.render(tooltip);
        UIComponentAPI deleteComponent = tooltip.getPrev();
        PositionAPI deletePosition = deleteComponent.getPosition();
        deletePosition.setXAlignOffset(getSize().getWidth() - deletePosition.getWidth() - 4);
        deletePosition.setYAlignOffset(getSize().getHeight());
        return deleteComponent;
    }

    private UIComponentAPI renderOnOff(TooltipMakerAPI tooltip, UIComponentAPI deleteComponent) {
        ToggleButton onOff = new OnOffButton(query);
        onOff.render(tooltip);
        UIComponentAPI onOffComponent = tooltip.getPrev();
        onOffComponent.getPosition().leftOfTop(deleteComponent, 10);
        return onOffComponent;
    }

    private UIComponentAPI renderPreview(TooltipMakerAPI tooltip, UIComponentAPI onOffComponent) {
        ToggleButton preview = new PreviewButton(query);
        preview.render(tooltip);
        UIComponentAPI previewComponent = tooltip.getPrev();
        previewComponent.getPosition().leftOfTop(onOffComponent, 1);
        return previewComponent;
    }
}
