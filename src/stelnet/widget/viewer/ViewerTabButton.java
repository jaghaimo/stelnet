package stelnet.widget.viewer;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.util.L10n;
import uilib.EventHandler;
import uilib.TabButton;

public class ViewerTabButton extends TabButton {

    public ViewerTabButton(
        final ContentRenderer newRenderer,
        final MarketViewState state,
        final boolean isActive,
        final int shortcut
    ) {
        super(L10n.widget(newRenderer.name()), isActive, shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    state.setContentRenderer(newRenderer);
                }
            }
        );
    }
}
