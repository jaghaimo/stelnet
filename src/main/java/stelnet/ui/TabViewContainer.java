package stelnet.ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fs.starfarer.api.ui.CustomPanelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import lombok.Setter;

@Setter
public class TabViewContainer extends AbstractRenderable {

    private final Map<Button, AbstractRenderable> tabs = new LinkedHashMap<>();
    private Button activeTab;

    public void addTab(Button tabButton, AbstractRenderable tabPanel, boolean isActive) {
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
    public void render(CustomPanelAPI panel) {
        if (tabs.isEmpty()) {
            return;
        }
        AbstractRenderable tabButtons = new HorizontalViewContainer(new ArrayList<AbstractRenderable>(tabs.keySet()));
        AbstractRenderable tabToDisplay = getTabToDisplay();
        new VerticalViewContainer(tabButtons, tabToDisplay).render(panel);
    }

    private AbstractRenderable getTabToDisplay() {
        if (tabs.containsKey(activeTab)) {
            return tabs.get(activeTab);
        }
        return tabs.get(tabs.keySet().iterator().next());
    }
}
