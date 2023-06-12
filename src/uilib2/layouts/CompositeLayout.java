package uilib2.layouts;

import com.fs.starfarer.api.campaign.CustomUIPanelPlugin;
import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.PositionAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import uilib2.Layout;

/**
 * A layout compromising of multiple panels.
 */
@RequiredArgsConstructor
public class CompositeLayout implements Layout {

    @Delegate
    private final List<Layout> layouts;

    private final CustomUIPanelPlugin plugin;

    public CompositeLayout() {
        this(new LinkedList<Layout>(), null);
    }

    public CompositeLayout(CustomUIPanelPlugin plugin) {
        this(new LinkedList<Layout>(), plugin);
    }

    public CompositeLayout(List<Layout> layouts) {
        this(layouts, null);
    }

    @Override
    public PositionAPI draw(CustomPanelAPI panel, float width, float height) {
        CustomPanelAPI subPanel = panel.createCustomPanel(width, height, plugin);
        for (Layout layout : layouts) {
            layout.draw(subPanel, width, height);
        }
        return panel.addComponent(subPanel);
    }
}
