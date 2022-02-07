package stelnet.board.query.view.list;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI.TooltipCreator;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import uilib.RenderableComponent;
import uilib.UiConstants;
import uilib.property.Size;

@RequiredArgsConstructor
public class PreviewTooltip implements TooltipCreator {

    private final Size size;
    private final Query query;

    @Override
    public boolean isTooltipExpandable(Object tooltipParam) {
        return false;
    }

    @Override
    public float getTooltipWidth(Object tooltipParam) {
        return size.getWidth() - UiConstants.DEFAULT_SPACER;
    }

    @Override
    public void createTooltip(TooltipMakerAPI tooltip, boolean expanded, Object tooltipParam) {
        RenderableComponent preview = query.getPreview(size);
        preview.render(tooltip);
    }
}
