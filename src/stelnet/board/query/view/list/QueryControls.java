package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import stelnet.board.query.Query;
import stelnet.widget.HeadingWithButtons;
import uilib.UiConstants;
import uilib.property.Size;

public class QueryControls extends HeadingWithButtons {

    private final Query query;

    public QueryControls(float width, Query query) {
        this.query = query;
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT));
        setWithScroller(false);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tooltip.setButtonFontVictor10();
        renderQueryHeading(tooltip, query.isEnabled(), query.getType() + " #" + query.getNumber());
        UIComponentAPI deleteComponent = renderFirst(new DeleteButton(query), getSize().getWidth(), tooltip);
        UIComponentAPI spacerComponent = addSpacer(tooltip, deleteComponent);
        UIComponentAPI onOffComponent = renderNext(new ToggleButton(query), tooltip, spacerComponent);
        UIComponentAPI purchasableComponent = renderNext(new PurchasableButton(query), tooltip, onOffComponent);
        renderNext(new PreviewButton(query), tooltip, purchasableComponent);
        tooltip.setButtonFontDefault();
    }

    private UIComponentAPI addSpacer(TooltipMakerAPI tooltip, UIComponentAPI previousComponent) {
        tooltip.addSpacer(0);
        UIComponentAPI spacerComponent = tooltip.getPrev();
        spacerComponent.getPosition().leftOfTop(previousComponent, UiConstants.DEFAULT_SPACER);
        return spacerComponent;
    }
}
