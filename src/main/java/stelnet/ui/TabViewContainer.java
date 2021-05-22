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
        AbstractRenderable tabButtons = new HorizontalViewContainer(new ArrayList<AbstractRenderable>(tabs.keySet()));
        AbstractRenderable tabToDisplay = new Paragraph("No idea what to display", 200);
        if (tabs.containsKey(activeTab)) {
            tabToDisplay = tabs.get(activeTab);
        }
        new VerticalViewContainer(tabButtons, tabToDisplay).render(panel);
    }
}
