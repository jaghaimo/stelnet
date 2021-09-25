package stelnet.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;

import lombok.Setter;
import stelnet.ui.property.Position;

@Setter
public class TabViewContainer extends AbstractRenderable {

    private final Map<Button, Renderable> tabs = new LinkedHashMap<>();
    private Button activeTab;

    public void addTab(Button tabButton, Renderable tabPanel, boolean isActive) {
        tabs.put(tabButton, tabPanel);
        if (isActive) {
            setActiveTab(tabButton);
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
        Renderable tabToDisplay = getTabToDisplay();
        HorizontalViewContainer tabButtons = new HorizontalViewContainer(new ArrayList<Renderable>(tabs.keySet()));
        Line separatorLine = new Line(tabToDisplay.getSize().getWidth(), Misc.getButtonTextColor());
        separatorLine.setOffset(new Position(0, -4));
        Spacer spacer = new Spacer(2);
        new VerticalViewContainer(tabButtons, separatorLine, spacer, tabToDisplay).render(panel, 0, 0);
    }

    private Renderable getTabToDisplay() {
        if (tabs.containsKey(activeTab)) {
            return tabs.get(activeTab);
        }
        return tabs.get(tabs.keySet().iterator().next());
    }
}
