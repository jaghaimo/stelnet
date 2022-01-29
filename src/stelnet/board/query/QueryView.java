package stelnet.board.query;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.lwjgl.input.Keyboard;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.board.query.view.add.AddQueryFactory;
import stelnet.board.query.view.list.QueryListFactory;
import stelnet.filter.Filter;
import uilib.HorizontalViewContainer;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.TabViewContainer;
import uilib.UiConstants;
import uilib.VerticalViewContainer;
import uilib.property.Position;
import uilib.property.Size;

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
        float previewOffset = UiConstants.DEFAULT_BUTTON_HEIGHT;
        float previewWidth = Math.max(250, size.getWidth() - 950);
        float previewHeight = size.getHeight() - previewOffset - 5;
        float width = size.getWidth() - previewWidth - 10;
        float height = size.getHeight() - 36;
        Size tabContentSize = new Size(width, height);
        TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size.reduce(new Size(previewWidth, 0)));
        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.LIST, Keyboard.KEY_L),
            getTabContent(queryListFactory, tabContentSize, QueryBoardTab.LIST),
            isActive(QueryBoardTab.LIST)
        );

        tabViewContainer.addTab(
            getTabButton(QueryBoardTab.NEW, Keyboard.KEY_N),
            getTabContent(addQueryFactory, tabContentSize, QueryBoardTab.NEW),
            isActive(QueryBoardTab.NEW)
        );

        RenderableComponent sideColumn = getSideColumn(previewWidth, previewHeight);
        Position offset = new Position(0, previewOffset);
        sideColumn.setOffset(offset);

        return Arrays.<Renderable>asList(new HorizontalViewContainer(tabViewContainer, sideColumn));
    }

    private RenderableComponent getSideColumn(float width, float height) {
        Size size = new Size(width, height);
        if (isActive(QueryBoardTab.NEW)) {
            Set<Filter> filters = addQueryFactory.getFilters(false);
            return addQueryFactory.getPreview(filters, size);
        }
        return queryListFactory.getButtons(size.reduce(new Size(UiConstants.DEFAULT_BUTTON_PADDING, 0)));
    }

    private QueryTabButton getTabButton(QueryBoardTab currentTab, int keyboardShortcut) {
        return new QueryTabButton(currentTab, activeTab, keyboardShortcut);
    }

    private VerticalViewContainer getTabContent(
        RenderableFactory factory,
        Size tabContentSize,
        QueryBoardTab currentTab
    ) {
        if (isActive(currentTab)) {
            return new VerticalViewContainer(factory.create(tabContentSize));
        }
        return null;
    }

    private boolean isActive(QueryBoardTab currentTab) {
        return currentTab.equals(activeTab);
    }
}
