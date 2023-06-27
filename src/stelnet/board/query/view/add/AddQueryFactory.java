package stelnet.board.query.view.add;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.board.query.provider.QueryProvider;
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.SizeHelper;
import stelnet.filter.Filter;
import stelnet.util.L10n;
import uilib.Button;
import uilib.CustomPanel;
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

    public void setQueryType(final QueryTypeButton active) {
        for (final QueryTypeButton button : queryType) {
            button.setStateOn(active.equals(button));
        }
    }

    @Override
    public List<Renderable> create(final Size size) {
        final float previewWidth = Math.max(250, size.getWidth() - 950);
        final float previewHeight = size.getHeight();
        final float width = size.getWidth() - previewWidth - 10;
        final float height = size.getHeight();
        final Size mainSize = new Size(width, height);
        final Size sideSize = new Size(previewWidth, previewHeight);
        setSizeHelper(new SizeHelper(mainSize));
        final RenderableComponent mainColumn = new CustomPanel(new VerticalViewContainer(getQueryBuildingComponents()));
        mainColumn.setSize(mainSize.increase(new Size(10, 0)));
        final RenderableComponent sideColumn = getPreview(getFilters(), sideSize);
        final RenderableComponent fullView = new HorizontalViewContainer(mainColumn, sideColumn);
        fullView.setSize(size);
        return Arrays.<Renderable>asList(fullView);
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
    public RenderableComponent getPreview(final Set<Filter> filters, final Size size) {
        return findNextFactory().getPreview(filters, size);
    }

    @Override
    public QueryProvider getProvider() {
        return findNextFactory().getProvider();
    }

    @Override
    protected List<Renderable> getQueryBuildingComponents() {
        final List<Renderable> elements = new LinkedList<>();
        elements.add(new Spacer(1));
        elements.add(new ButtonGroup(sizeHelper, L10n.query("QUERY_TYPE"), queryType, true));
        final QueryFactory nextFactory = findNextFactory();
        elements.addAll(nextFactory.getQueryBuildingComponents());
        elements.add(new Spacer(UiConstants.DEFAULT_SPACER));
        elements.add(new ButtonGroup(sizeHelper, null, getFinalComponents(), true));
        return elements;
    }

    private QueryTypeButton[] createQueryTypeButtons() {
        final QueryTypeButton[] queryType = new QueryTypeButton[] {
            new QueryTypeButton(this, L10n.common("PERSONNEL"), new PersonnelQueryFactory()),
            new QueryTypeButton(this, L10n.common("ITEMS"), new ItemQueryFactory()),
            new QueryTypeButton(this, L10n.common("SHIPS"), new ShipQueryFactory()),
        };
        queryType[0].setStateOn(true);
        return queryType;
    }

    private QueryFactory findNextFactory() {
        for (final QueryTypeButton button : queryType) {
            if (button.isStateOn()) {
                final QueryFactory queryFactory = button.getNextFactory();
                queryFactory.setSizeHelper(sizeHelper);
                return queryFactory;
            }
        }
        log.error("Did not find any factory to use, returning a DummyFactory!");
        return new DummyFactory();
    }
}
