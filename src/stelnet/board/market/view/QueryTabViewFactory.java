package stelnet.board.market.view;

import com.fs.starfarer.api.ui.Alignment;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.lwjgl.input.Keyboard;
import stelnet.board.market.QueryState;
import stelnet.board.market.QueryState.QueryBoardTab;
import uilib.DynamicGroup;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.TabViewContainer;
import uilib.VerticalViewContainer;
import uilib.ViewContainerFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryTabViewFactory implements ViewContainerFactory {

    private final QueryBoardTab activeTab;
    private final QueryTypeFactory queryTypeFactory;

    public QueryTabViewFactory(QueryState queryState) {
        activeTab = queryState.getActiveTab();
        queryTypeFactory = queryState.getQueryTypeFactory();
    }

    @Override
    public Renderable createContainer(Size size) {
        float width = size.getWidth() - 10;
        float height = size.getHeight();
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size);
        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.LIST, Keyboard.KEY_L),
            getQueryListTab(width, height),
            isActive(QueryBoardTab.LIST)
        );

        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.NEW, Keyboard.KEY_N),
            getNewQueryTab(width, height),
            isActive(QueryBoardTab.NEW)
        );

        return tabViewContainer;
    }

    private Renderable getQueryListTab(float width, float tableHeight) {
        return new VerticalViewContainer(new Paragraph("No queries to be shown.", width));
    }

    private Renderable getNewQueryTab(float width, float tableHeight) {
        float textWidth = Math.max(width / 4, 200);
        float groupWidth = width - textWidth;
        List<Renderable> elements = new LinkedList<>();
        elements.add(
            new HorizontalViewContainer(
                new Paragraph("What would you like to search for?", textWidth, 4, Alignment.RMID),
                new DynamicGroup(groupWidth, queryTypeFactory.getButtons())
            )
        );
        return new VerticalViewContainer(elements);
    }

    private QueryTabButton getTabButton(QueryBoardTab currentTab, int keyboardShortcut) {
        return new QueryTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(QueryBoardTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
