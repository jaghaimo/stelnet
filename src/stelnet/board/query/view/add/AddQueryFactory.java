package stelnet.board.query.view.add;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.CommonL10n;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.provider.QueryProvider;
import stelnet.filter.Filter;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.property.Location;
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
        return getQueryBuilder();
    }

    public void setQueryType(QueryTypeButton active) {
        for (QueryTypeButton button : queryType) {
            button.setStateOn(active.equals(button));
        }
    }

    @Override
    public RenderableComponent getPreview(Size size) {
        QueryFactory queryFactory = findNextFactory();
        RenderableComponent preview = queryFactory.getPreview(size);
        preview.setLocation(Location.TOP_RIGHT);
        return preview;
    }

    @Override
    protected Set<Filter> getFilters() {
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
    protected QueryProvider getProvider() {
        return findNextFactory().getProvider();
    }

    private void addFromNextFactory(List<Renderable> elements) {
        QueryFactory nextFactory = findNextFactory();
        elements.addAll(nextFactory.getQueryBuilder());
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
