package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import stelnet.board.query.Query;
import stelnet.widget.heading.HeadingWithButtons;
import uilib.UiConstants;
import uilib.property.Size;

public class QueryControls extends HeadingWithButtons {

    private final Query query;

    public QueryControls(final float width, final Query query) {
        this.query = query;
        setSize(new Size(width, UiConstants.DEFAULT_ROW_HEIGHT));
        setWithScroller(false);
    }

    @Override
    public void render(final TooltipMakerAPI tooltip) {
        tooltip.setButtonFontVictor10();
        renderQueryHeading(tooltip, query.isEnabled(), query.getType() + " #" + query.getNumber());
        final UIComponentAPI deleteComponent = renderFirstButton(
            new DeleteButton(query),
            getSize().getWidth(),
            tooltip
        );
        final UIComponentAPI spacerComponent = addSpacer(tooltip, deleteComponent);
        final UIComponentAPI onOffComponent = renderNextButton(new OnOffButton(query), tooltip, spacerComponent);
        renderNextButton(new PreviewButton(query), tooltip, onOffComponent);
        tooltip.setButtonFontDefault();
    }

    private UIComponentAPI addSpacer(final TooltipMakerAPI tooltip, final UIComponentAPI previousComponent) {
        tooltip.addSpacer(0);
        final UIComponentAPI spacerComponent = tooltip.getPrev();
        spacerComponent.getPosition().leftOfTop(previousComponent, UiConstants.DEFAULT_SPACER);
        return spacerComponent;
    }
}
