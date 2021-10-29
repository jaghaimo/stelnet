package stelnet.widget.viewer;

import com.fs.starfarer.api.ui.IntelUIAPI;
import stelnet.util.L10n;
import uilib.EventHandler;
import uilib.TabButton;

public class ViewerTabButton extends TabButton {

    public ViewerTabButton(
        final ContentRenderer newRenderer,
        final MarketViewState state,
        boolean isActive,
        int shortcut
    ) {
        super(L10n.get("viewTab" + newRenderer.id), isActive, shortcut);
        setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    state.setContentRenderer(newRenderer);
                }
            }
        );
    }
}
