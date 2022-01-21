package stelnet.board.query.view.result;

import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.ui.UIComponentAPI;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Result;
import uilib.RenderableComponent;
import uilib.UiConstants;

@RequiredArgsConstructor
public class ResultDisplay extends RenderableComponent {

    private final float width;
    private final Result result;

    @Override
    public void render(TooltipMakerAPI tooltip) {
        if (result.isPerson()) {
            UIComponentAPI panel = tooltip.addSkillPanel(result.getPerson(), UiConstants.DEFAULT_SPACER);
            float height = panel.getPosition().getHeight();
            panel.getPosition().setSize(width, height);
        }
        // TODO Finish this method
    }
}
