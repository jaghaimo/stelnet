package uilib;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import stelnet.util.SettingsUtils;
import uilib.property.Position;
import uilib.property.Size;

@Getter
@Setter
public class TabViewContainer extends RenderableComponent {

    private final Map<Button, Renderable> tabs = new LinkedHashMap<>();
    private Button activeTab;
    private Size outerContainerSizeCorrection = new Size(0, 35);

    public void addTab(Button tabButton, Renderable tabPanel, boolean isActive) {
        tabs.put(tabButton, tabPanel);
        if (isActive) {
            setActiveTab(tabButton);
        }
        if (tabs.size() > 1) {
            tabButton.setOffset(new Position(1, 0));
        }
    }

    @Override
    public void render(TooltipMakerAPI tooltip) {
        tabs.get(activeTab).render(tooltip);
    }

    @Override
    public void render(CustomPanelAPI panel, float x, float y) {
        if (tabs.isEmpty()) {
            return;
        }
        Renderable tabToDisplay = getTabToDisplay(panel);
        HorizontalViewContainer tabButtons = new HorizontalViewContainer(new ArrayList<Renderable>(tabs.keySet()));
        Line separatorLine = new Line(tabToDisplay.getSize().getWidth() - 6, SettingsUtils.getButtonHighlightColor());
        separatorLine.setPadding(0);
        Spacer spacer = new Spacer(10);
        new VerticalViewContainer(tabButtons, separatorLine, spacer, tabToDisplay).render(panel, x, y);
    }

    private Renderable getTabToDisplay(CustomPanelAPI panel) {
        Renderable tabToDisplay = getActiveTab();
        CustomPanel customPanel = new CustomPanel(tabToDisplay);
        customPanel.setSize(tabToDisplay.getSize());
        customPanel.render(panel, 0, 0);
        Group outerContainer = new Group(customPanel);
        outerContainer.setSize(getSize().reduce(outerContainerSizeCorrection));
        outerContainer.setWithScroller(true);
        return outerContainer;
    }

    private Renderable getActiveTab() {
        if (tabs.containsKey(activeTab)) {
            return tabs.get(activeTab);
        }
        return tabs.get(tabs.keySet().iterator().next());
    }
}
