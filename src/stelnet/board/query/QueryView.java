package stelnet.board.query;

import java.util.Collections;
import java.util.List;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.board.query.view.add.AddQueryFactory;
import stelnet.board.query.view.list.QueryListFactory;
import stelnet.board.query.view.manage.ManageResultsFactory;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.TabViewContainer;
import uilib.VerticalViewContainer;
import uilib.property.Size;

public class QueryView implements RenderableFactory {

    private final QueryBoardTab activeTab;
    private final AddQueryFactory addQueryFactory;
    private final QueryListFactory queryListFactory;
    private final ManageResultsFactory manageResultsFactory;

    public QueryView(final QueryState queryState) {
        activeTab = queryState.getActiveTab();
        addQueryFactory = queryState.getAddQueryFactory();
        queryListFactory = queryState.getQueryListFactory();
        manageResultsFactory = queryState.getManageResultsFactory();
    }

    @Override
    public List<Renderable> create(final Size size) {
        final float width = size.getWidth() - 10;
        final float height = size.getHeight() - 36;
        final Size tabContentSize = new Size(width, height);
        final TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size);
        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.LIST, Keyboard.KEY_L),
            getTabContent(queryListFactory, tabContentSize, QueryBoardTab.LIST),
            isActive(QueryBoardTab.LIST)
        );
        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.MANAGE, Keyboard.KEY_M),
            getTabContent(manageResultsFactory, tabContentSize, QueryBoardTab.MANAGE),
            isActive(QueryBoardTab.MANAGE)
        );
        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.NEW, Keyboard.KEY_N),
            getTabContent(addQueryFactory, tabContentSize, QueryBoardTab.NEW),
            isActive(QueryBoardTab.NEW)
        );
        return Collections.<Renderable>singletonList(tabViewContainer);
    }

    private QueryTabButton getTabButton(final QueryBoardTab currentTab, final int keyboardShortcut) {
        return new QueryTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private VerticalViewContainer getTabContent(
        final RenderableFactory factory,
        final Size tabContentSize,
        final QueryBoardTab currentTab
    ) {
        if (isActive(currentTab)) {
            return new VerticalViewContainer(factory.create(tabContentSize));
        }
        return null;
    }

    private boolean isActive(final QueryBoardTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
