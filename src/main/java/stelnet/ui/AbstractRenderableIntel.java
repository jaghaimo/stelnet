package stelnet.ui;

import java.util.List;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.helper.LogHelper;

public abstract class AbstractRenderableIntel extends BaseIntelPlugin {
    @Override
    public void buttonPressCancelled(Object buttonId, IntelUIAPI ui) {
        LogHelper.debug("Calling cancel()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onCancel(ui);
        redraw(ui);
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        LogHelper.debug("Calling confirm()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onConfirm(ui);
        redraw(ui);
    }

    @Override
    public void createConfirmationPrompt(Object buttonId, TooltipMakerAPI prompt) {
        LogHelper.debug("Calling prompt()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onPrompt(prompt);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        for (Renderable renderable : getRenderables(width, height)) {
            renderable.render(info);
        }
    }

    @Override
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        for (Renderable renderable : getRenderables(width, height)) {
            renderable.render(panel);
        }
    }

    @Override
    public boolean doesButtonHaveConfirmDialog(Object buttonId) {
        LogHelper.debug("Calling hasPrompt()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        return handler.hasPrompt();
    }

    @Override
    public boolean hasLargeDescription() {
        return false;
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }

    protected abstract List<Renderable> getRenderables(float width, float height);

    private void redraw(IntelUIAPI ui) {
        ui.recreateIntelUI();
        ui.updateUIForItem(this);
    }
}
