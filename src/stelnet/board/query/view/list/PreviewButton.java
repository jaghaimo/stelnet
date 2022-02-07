package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.CutStyle;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipLocation;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import uilib.property.Size;

public class PreviewButton extends ControlButton {

    private final Query query;

    public PreviewButton(final Query query) {
        super(QueryL10n.PREVIEW, QueryL10n.PREVIEW, true, true);
        this.query = query;
        setCutStyle(CutStyle.C2_MENU);
        scaleButton(query);
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        super.render(tooltip);
        Size size = new Size(300, 0);
        tooltip.addTooltipToPrevious(new PreviewTooltip(size, query), TooltipLocation.LEFT);
    }
}
