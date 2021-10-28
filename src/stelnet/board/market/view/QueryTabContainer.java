package stelnet.board.market.view;

import lombok.RequiredArgsConstructor;
import org.lwjgl.input.Keyboard;
import stelnet.board.market.QueryState;
import stelnet.board.market.QueryState.QueryBoardTab;
import stelnet.board.market.view.newquery.QueryTypeContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.TabViewContainer;
import uilib.VerticalViewContainer;
import uilib.ViewContainerFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryTabContainer implements ViewContainerFactory {

    private final QueryBoardTab activeTab;
    private final QueryTypeContainer queryTypeFactory;

    public QueryTabContainer(QueryState queryState) {
        activeTab = queryState.getActiveTab();
        queryTypeFactory = queryState.getQueryTypeFactory();
    }

    @Override
    public Renderable create(Size size) {
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
            queryTypeFactory.create(new Size(width, height)),
            isActive(QueryBoardTab.NEW)
        );

        return tabViewContainer;
    }

    private Renderable getQueryListTab(float width, float tableHeight) {
        return new VerticalViewContainer(new Paragraph("No queries to be shown.", width));
    }

    private QueryTabButton getTabButton(QueryBoardTab currentTab, int keyboardShortcut) {
        return new QueryTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(QueryBoardTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
