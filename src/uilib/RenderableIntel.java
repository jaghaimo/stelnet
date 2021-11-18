package uilib;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.Color;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j;
import uilib.property.Size;

@Log4j
public abstract class RenderableIntel extends BaseIntelPlugin {

    @Override
    public void buttonPressCancelled(Object buttonId, IntelUIAPI ui) {
        log.debug("Calling cancel()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onCancel(ui);
        redraw(ui);
    }

    @Override
    public void buttonPressConfirmed(Object buttonId, IntelUIAPI ui) {
        log.debug("Calling confirm()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onConfirm(ui);
        redraw(ui);
    }

    @Override
    public void createConfirmationPrompt(Object buttonId, TooltipMakerAPI prompt) {
        log.debug("Calling prompt()");
        ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onPrompt(prompt);
    }

    @Override
    public void createIntelInfo(TooltipMakerAPI info, ListInfoMode mode) {
        RenderableIntelInfo intelInfo = getIntelInfo();
        Color bulletColor = getBulletColorForMode(mode);
        Color titleColor = getTitleColor(mode);
        intelInfo.render(info, bulletColor, titleColor);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        Size size = new Size(width, height);
        for (Renderable view : getRenderableList(size)) {
            view.render(info);
        }
    }

    @Override
    public void createLargeDescription(CustomPanelAPI panel, float width, float height) {
        Size size = new Size(width, height);
        for (Renderable view : getRenderableList(size)) {
            view.render(panel, 0, 0);
        }
    }

    @Override
    public boolean doesButtonHaveConfirmDialog(Object buttonId) {
        log.debug("Calling hasPrompt()");
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

    @Override
    public boolean isNew() {
        return false;
    }

    protected List<Renderable> getRenderableList(Size size) {
        return Collections.<Renderable>emptyList();
    }

    protected abstract RenderableIntelInfo getIntelInfo();

    private void redraw(IntelUIAPI ui) {
        ui.recreateIntelUI();
        ui.updateUIForItem(this);
    }
}
