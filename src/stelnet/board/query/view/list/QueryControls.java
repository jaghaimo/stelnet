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
        overlapQueryHeading(tooltip);
        UIComponentAPI deleteComponent = renderDelete(tooltip);
        UIComponentAPI onOffComponent = renderButton(new OnOffButton(query), tooltip, deleteComponent);
        UIComponentAPI purchasableComponent = renderButton(new PurchasableButton(query), tooltip, onOffComponent);
        renderButton(new PreviewButton(query), tooltip, purchasableComponent);
        tooltip.setButtonFontDefault();
    }

    private void renderHeading(TooltipMakerAPI tooltip) {
        tooltip.setParaFontVictor14();
        Heading heading = new Heading("", Misc.getBasePlayerColor(), Misc.getDarkPlayerColor());
        if (!query.isEnabled()) {
            heading.setForegroundColor(Misc.scaleAlpha(Misc.getBasePlayerColor(), 0.3f));
            heading.setBackgroundColor(Misc.scaleAlpha(Misc.getDarkPlayerColor(), 0.2f));
        }
        heading.setAlignment(Alignment.LMID);
        heading.render(tooltip);
    }

    private void overlapQueryHeading(TooltipMakerAPI tooltip) {
        tooltip.setParaFontVictor14();
        tooltip.setParaFontColor(Misc.getBrightPlayerColor());
        if (!query.isEnabled()) {
            tooltip.setParaFontColor(Misc.scaleAlpha(Misc.getBrightPlayerColor(), 0.2f));
        }
        tooltip.addPara(query.getType() + " #" + query.getNumber(), 0);
        tooltip.getPrev().getPosition().setYAlignOffset(16);
        tooltip.addSpacer(0);
        tooltip.getPrev().getPosition().setYAlignOffset(-3);
    }

    private UIComponentAPI renderDelete(TooltipMakerAPI tooltip) {
        ToggleButton delete = new DeleteButton(query);
        delete.render(tooltip);
        UIComponentAPI deleteComponent = tooltip.getPrev();
        PositionAPI deletePosition = deleteComponent.getPosition();
        deletePosition.setXAlignOffset(getSize().getWidth() - deletePosition.getWidth() - 5);
        deletePosition.setYAlignOffset(getSize().getHeight());
        tooltip.addSpacer(0);
        UIComponentAPI spacerComponent = tooltip.getPrev();
        spacerComponent.getPosition().leftOfTop(deleteComponent, UiConstants.DEFAULT_SPACER);
        return spacerComponent;
    }

    private UIComponentAPI renderButton(
        ToggleButton button,
        TooltipMakerAPI tooltip,
        UIComponentAPI previousComponent
    ) {
        button.render(tooltip);
        UIComponentAPI currentComponent = tooltip.getPrev();
        currentComponent.getPosition().leftOfTop(previousComponent, 1);
        return currentComponent;
    }
}
