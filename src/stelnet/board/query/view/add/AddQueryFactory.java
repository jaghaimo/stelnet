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
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.SizeHelper;
import stelnet.filter.Filter;
import uilib.Button;
import uilib.HorizontalViewContainer;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.VerticalViewContainer;
import uilib.property.Size;

@Getter
@Log4j
public class AddQueryFactory extends QueryFactory implements RenderableFactory {

    private final QueryTypeButton[] queryType = createQueryTypeButtons();

    public void setQueryType(QueryTypeButton active) {
        for (QueryTypeButton button : queryType) {
            button.setStateOn(active.equals(button));
        }
    }

    @Override
    public List<Renderable> create(Size size) {
        float previewWidth = Math.max(250, size.getWidth() - 950);
        float previewHeight = size.getHeight();
        float width = size.getWidth() - previewWidth - 10;
        float height = size.getHeight() - 36;
        Size mainSize = new Size(width, height);
        Size sideSize = new Size(previewWidth, previewHeight);
        setSizeHelper(new SizeHelper(mainSize));
        RenderableComponent mainColumn = new VerticalViewContainer(getQueryBuildingComponents());
        mainColumn.setSize(new Size(width, mainColumn.getSize().getHeight()));
        RenderableComponent sideColumn = getPreview(getFilters(), sideSize);
        return Arrays.<Renderable>asList(new HorizontalViewContainer(mainColumn, sideColumn));
    }

    @Override
    public Set<Filter> getFilters() {
        return findNextFactory().getFilters();
    }

    @Override
    public Button[] getFinalComponents() {
        return findNextFactory().getFinalComponents();
    }

    @Override
    public RenderableComponent getPreview(Set<Filter> filters, Size size) {
        return findNextFactory().getPreview(filters, size);
    }

    @Override
    public QueryProvider getProvider() {
        return findNextFactory().getProvider();
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new Spacer(1));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.QUERY_TYPE, queryType, true));
        QueryFactory nextFactory = findNextFactory();
        elements.addAll(nextFactory.getQueryBuildingComponents());
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.add(new ButtonGroup(sizeHelper, null, getFinalComponents(), true));
        return elements;
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
