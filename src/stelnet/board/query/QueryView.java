package stelnet.board.query;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.board.query.view.add.AddQueryFactory;
import stelnet.board.query.view.list.QueryListFactory;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.TabViewContainer;
import uilib.VerticalViewContainer;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryView implements RenderableFactory {

    private final QueryBoardTab activeTab;
    private final AddQueryFactory addQueryFactory;
    private final QueryListFactory queryListFactory;

    public QueryView(QueryState queryState) {
        activeTab = queryState.getActiveTab();
        addQueryFactory = queryState.getQueryTypeFactory();
        queryListFactory = new QueryListFactory();
    }

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth() - 10;
        float height = size.getHeight();
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size);
        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.LIST, Keyboard.KEY_L),
            new VerticalViewContainer(queryListFactory.create(new Size(width, height))),
            isActive(QueryBoardTab.LIST)
        );

        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.NEW, Keyboard.KEY_N),
            new VerticalViewContainer(addQueryFactory.create(new Size(width, height))),
            isActive(QueryBoardTab.NEW)
        );

        return Collections.<Renderable>singletonList(tabViewContainer);
    }

    private QueryTabButton getTabButton(QueryBoardTab currentTab, int keyboardShortcut) {
        return new QueryTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(QueryBoardTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
