package stelnet.board.query;

import java.util.Arrays;
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
        addQueryFactory = queryState.getAddQueryFactory();
        queryListFactory = queryState.getQueryListFactory();
    }

    @Override
    public List<Renderable> create(Size size) {
        float previewWidth = Math.max(250, size.getWidth() - 950);
        float previewHeight = size.getHeight();
        float width = size.getWidth() - previewWidth - 10;
        float height = size.getHeight() - 36;
        Size tabContentSize = new Size(width, height);
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size.reduce(new Size(previewWidth, 0)));
        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.LIST, Keyboard.KEY_L),
            new VerticalViewContainer(queryListFactory.create(tabContentSize)),
            isActive(QueryBoardTab.LIST)
        );

        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.NEW, Keyboard.KEY_N),
            new VerticalViewContainer(addQueryFactory.create(tabContentSize)),
            isActive(QueryBoardTab.NEW)
        );

        return Arrays.<Renderable>asList(tabViewContainer, getPreview(previewWidth, previewHeight));
    }

    private Renderable getPreview(float width, float height) {
        return addQueryFactory.getPreview(new Size(width, height));
    }

    private QueryTabButton getTabButton(QueryBoardTab currentTab, int keyboardShortcut) {
        return new QueryTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private boolean isActive(QueryBoardTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
