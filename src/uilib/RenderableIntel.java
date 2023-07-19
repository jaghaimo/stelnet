package uilib;

import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.IntelUIAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.awt.*;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j;
import uilib.property.Size;

@Log4j
public abstract class RenderableIntel extends BaseIntelPlugin {

    @Override
    public void createIntelInfo(final TooltipMakerAPI info, final ListInfoMode mode) {
        final RenderableIntelInfo intelInfo = getIntelInfo();
        final Color bulletColor = getBulletColorForMode(mode);
        final Color titleColor = getTitleColor(mode);
        intelInfo.render(info, bulletColor, titleColor);
    }

    @Override
    public boolean hasSmallDescription() {
        return false;
    }

    @Override
    public void createSmallDescription(final TooltipMakerAPI info, final float width, final float height) {
        final long startTime = System.currentTimeMillis();
        final Size size = new Size(width, height);
        for (final Renderable view : getRenderableList(size)) {
            view.render(info);
        }
        final long stopTime = System.currentTimeMillis();
        log.debug(String.format("Created small intel in %dms", stopTime - startTime));
    }

    @Override
    public boolean hasLargeDescription() {
        return false;
    }

    @Override
    public void createLargeDescription(final CustomPanelAPI panel, final float width, final float height) {
        final long startTime = System.currentTimeMillis();
        final Size size = new Size(width, height);
        for (final Renderable view : getRenderableList(size)) {
            view.render(panel, 0, 0);
        }
        final long stopTime = System.currentTimeMillis();
        log.debug(String.format("Created large intel in %dms", stopTime - startTime));
    }

    @Override
    public boolean doesButtonHaveConfirmDialog(final Object buttonId) {
        log.debug("Calling hasPrompt()");
        final ButtonHandler handler = (ButtonHandler) buttonId;
        return handler.hasPrompt();
    }

    @Override
    public void createConfirmationPrompt(final Object buttonId, final TooltipMakerAPI prompt) {
        log.debug("Calling prompt()");
        final ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onPrompt(prompt);
    }

    @Override
    public void buttonPressConfirmed(final Object buttonId, final IntelUIAPI ui) {
        final long startTime = System.currentTimeMillis();
        log.debug("Calling confirm()");
        final ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onConfirm(ui);
        redraw(ui);
        final long stopTime = System.currentTimeMillis();
        log.debug(String.format("Processed button click in %dms", stopTime - startTime));
    }

    @Override
    public void buttonPressCancelled(final Object buttonId, final IntelUIAPI ui) {
        log.debug("Calling cancel()");
        final ButtonHandler handler = (ButtonHandler) buttonId;
        handler.onCancel(ui);
        redraw(ui);
    }

    @Override
    public boolean isNew() {
        return false;
    }

    @Override
    public void bullet(final TooltipMakerAPI info) {
        super.bullet(info);
    }

    @Override
    public void indent(final TooltipMakerAPI info) {
        super.indent(info);
    }

    @Override
    public void unindent(final TooltipMakerAPI info) {
        super.unindent(info);
    }

    protected List<Renderable> getRenderableList(final Size size) {
        return Collections.emptyList();
    }

    protected abstract RenderableIntelInfo getIntelInfo();

    private void redraw(final IntelUIAPI ui) {
        ui.recreateIntelUI();
        ui.updateUIForItem(this);
    }
}
