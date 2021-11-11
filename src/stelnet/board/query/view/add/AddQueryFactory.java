package stelnet.board.query.view.add;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import uilib.Heading;
import uilib.HorizontalViewContainer;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.VerticalViewContainer;
import uilib.property.Size;

@Getter
@Log4j
public class AddQueryFactory extends QueryFactory implements RenderableFactory {

    private transient QueryTypeButton[] queryType = createQueryTypeButtons();
    private transient SearchButton[] searchButton = createSearchButtons();

    public void readResolve() {
        queryType = createQueryTypeButtons();
        searchButton = createSearchButtons();
    }

    @Override
    public List<Renderable> create(Size size) {
        setSizeHelper(new SizeHelper(size));
        Renderable container = new VerticalViewContainer(getQueryBuilder());
        Size previewSize = new Size(sizeHelper.getPreviewWidth(), sizeHelper.getHeight());
        Renderable padding = new Spacer(12);
        Renderable preview = buildPreview(getPreview(previewSize), previewSize);
        HorizontalViewContainer horizontalViewContainer = new HorizontalViewContainer(container, padding, preview);
        return Collections.<Renderable>singletonList(horizontalViewContainer);
    }

    public void setQueryType(QueryTypeButton active) {
        for (QueryTypeButton button : queryType) {
            button.setStateOn(active.equals(button));
        }
    }

    @Override
    protected List<Filter> getFilters() {
        return findNextFactory().getFilters();
    }

    @Override
    protected List<Renderable> getQueryBuilder() {
        List<Renderable> elements = new LinkedList<>();
        addLabeledGroup(elements, QueryL10n.QUERY_TYPE, Arrays.<Renderable>asList(queryType));
        addFromNextFactory(elements);
        addSpacer(elements, 10);
        addLabeledGroup(elements, null, Arrays.<Renderable>asList(searchButton));
        return elements;
    }

    @Override
    protected RenderableComponent getPreview(Size size) {
        QueryFactory queryFactory = findNextFactory();
        return queryFactory.getPreview(size);
    }

    @Override
    protected QueryProvider getProvider() {
        return findNextFactory().getProvider();
    }

    private void addFromNextFactory(List<Renderable> elements) {
        QueryFactory nextFactory = findNextFactory();
        elements.addAll(nextFactory.getQueryBuilder());
    }

    private Renderable buildPreview(RenderableComponent content, Size size) {
        final float HEADING_HEIGHT = 25;
        final float RESERVED_FOR_TABS_AND_PADDING = 33 + 4;
        Heading heading = new Heading("Preview");
        heading.setSize(new Size(size.getWidth(), HEADING_HEIGHT));
        content.setSize(size.reduce(new Size(0, HEADING_HEIGHT + RESERVED_FOR_TABS_AND_PADDING)));
        VerticalViewContainer verticalView = new VerticalViewContainer(heading, content);
        verticalView.setSize(size.reduce(new Size(0, RESERVED_FOR_TABS_AND_PADDING)));
        return verticalView;
    }

    private QueryTypeButton[] createQueryTypeButtons() {
        QueryTypeButton[] queryType = new QueryTypeButton[] {
            new QueryTypeButton(this, CommonL10n.PERSONNEL, new PersonnelQueryFactory()),
            new QueryTypeButton(this, CommonL10n.ITEMS, new ItemQueryFactory()),
            new QueryTypeButton(this, CommonL10n.SHIPS, new ShipQueryFactory()),
        };
        queryType[0].setStateOn(true);
        return queryType;
    }

    private SearchButton[] createSearchButtons() {
        return new SearchButton[] {
            new SearchButton(this, QueryL10n.SEARCH_MATCHING),
            new SearchButton(this, QueryL10n.SEARCH_SELECTED),
        };
    }

    private QueryFactory findNextFactory() {
        for (QueryTypeButton button : queryType) {
            if (button.isStateOn()) {
                QueryFactory queryFactory = button.getNextFactory();
                queryFactory.setSizeHelper(sizeHelper);
                return queryFactory;
            }
        }
        log.error("Did not find any factory to use, returning a DummyFactory!");
        return new DummyFactory();
    }
}
