package stelnet.market.view;

import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.lwjgl.input.Keyboard;
import stelnet.market.QueryTab;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.TabViewContainer;
import uilib.ToggleButton;
import uilib.VerticalViewContainer;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryTabViewFactory {

    private final QueryTab activeTab;

    public Renderable createContainer(Size size) {
        float width = size.getWidth() - 10;
        float height = size.getHeight();
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size);
        tabViewContainer.addTab(
            getTabButton(QueryTab.LIST, Keyboard.KEY_L),
            getQueryListTab(width, height),
            isActive(QueryTab.LIST)
        );

        tabViewContainer.addTab(
            getTabButton(QueryTab.NEW, Keyboard.KEY_N),
            getNewQueryTab(width, height),
            isActive(QueryTab.NEW)
        );

        return tabViewContainer;
    }

    private Renderable getQueryListTab(float width, float tableHeight) {
        return new VerticalViewContainer(
            new Paragraph("No queries to be shown.", width)
        );
    }

    private Renderable getNewQueryTab(float width, float tableHeight) {
        float textWidth = Math.max(width / 4, 200);
        float groupWidth = width - textWidth;
        List<Renderable> elements = new LinkedList<>();
        elements.add(
            new HorizontalViewContainer(
                new Paragraph(
                    "What would you like to search for?",
                    textWidth,
                    4,
                    Alignment.RMID
                ),
                new DynamicGroup(groupWidth, getButtons1())
            )
        );
        elements.add(
            new HorizontalViewContainer(
                new Paragraph(
                    "What type of items to look for?",
                    textWidth,
                    4,
                    Alignment.RMID
                ),
                new DynamicGroup(groupWidth, getButtons2())
            )
        );
        return new VerticalViewContainer(elements);
    }

    private QueryTabButton getTabButton(
        QueryTab currentTab,
        int keyboardShortcut
    ) {
        return new QueryTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(QueryTab currentTab) {
        return currentTab.equals(activeTab);
    }

    private List<Renderable> getButtons1() {
        Color colorOn = Misc.getButtonTextColor();
        Color colorOff = Misc.getGrayColor();
        return Arrays.<Renderable>asList(
            new ToggleButton(
                new Size(0, 20),
                "Officers",
                "Officers",
                true,
                colorOn,
                colorOff,
                false
            ),
            new ToggleButton(
                new Size(0, 20),
                "Items",
                "Items",
                true,
                colorOn,
                colorOff,
                true
            ),
            new ToggleButton(
                new Size(0, 20),
                "Ships",
                "Ships",
                true,
                colorOn,
                colorOff,
                false
            )
        );
    }

    private List<Renderable> getButtons2() {
        Color colorOn = Misc.getButtonTextColor();
        Color colorOff = Misc.getGrayColor();
        return Arrays.<Renderable>asList(
            new ToggleButton(
                new Size(0, 20),
                "Weapons",
                "Weapons",
                true,
                colorOn,
                colorOff,
                true
            ),
            new ToggleButton(
                new Size(0, 20),
                "LPC",
                "LPC",
                true,
                colorOn,
                colorOff,
                false
            ),
            new ToggleButton(
                new Size(0, 20),
                "Modspecs",
                "Modspecs",
                true,
                colorOn,
                colorOff,
                false
            )
        );
    }

    private List<Renderable> getButtons3() {
        return null;
    }

    private List<Renderable> getButtons4() {
        return null;
    }

    private List<Renderable> getButtons5() {
        return null;
    }

    private List<Renderable> getButtons6() {
        return null;
    }
}
