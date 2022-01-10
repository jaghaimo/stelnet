package stelnet.board.query.view.list;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.util.L10n;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Location;
import uilib.property.Size;

@RequiredArgsConstructor
public class QueryListFactory implements RenderableFactory {

    private final QueryManager manager;

    @Override
    public List<Renderable> create(Size size) {
        float width = size.getWidth();
        List<Renderable> elements = new LinkedList<>();
        for (Query query : manager.getQueries()) {
            elements.add(new QueryRow(width, query));
        }
        Collections.reverse(elements);
        boolean enableButtons = addNewQueries(elements, width);
        elements.add(0, new Spacer(2 * UiConstants.DEFAULT_SPACER));
        elements.add(0, getGroupingButtons(enableButtons, size.getWidth()));
        elements.add(0, getGlobalButtons(enableButtons, size.getWidth()));
        return elements;
    }

    public RenderableComponent getFilters(Size size) {
        RenderableComponent preview = new Spacer(0);
        preview.setLocation(Location.TOP_RIGHT);
        return preview;
    }

    private boolean addNewQueries(List<Renderable> elements, float width) {
        boolean hasAdded = false;
        if (elements.isEmpty()) {
            hasAdded = true;
            elements.add(new Paragraph(L10n.get(QueryL10n.NO_QUERIES), width));
        }
        return !hasAdded;
    }

    private void adjustSpacerWidth(List<Renderable> elements, Spacer spacer, float width) {
        float spacerWidth = calculateRemaining(width, elements);
        spacer.setSize(new Size(spacerWidth, 0));
    }

    private float calculateRemaining(float width, List<Renderable> elements) {
        float total = 0;
        for (Renderable element : elements) {
            total += element.getSize().getWidth();
        }
        return Math.max(0, width - total);
    }

    private Renderable getGlobalButtons(boolean enableButtons, float width) {
        Spacer spacer = new Spacer(0);
        List<Renderable> elements = new LinkedList<>();
        elements.add(new RefreshAllButton(manager, enableButtons));
        elements.add(new EnableAllButton(manager, enableButtons));
        elements.add(new DisableAllButton(manager, enableButtons));
        elements.add(spacer);
        elements.add(new DeleteAllButton(manager, enableButtons));
        adjustSpacerWidth(elements, spacer, width);
        return new HorizontalViewContainer(elements);
    }

    private Renderable getGroupingButtons(boolean enableButtons, float width) {
        float buttonWidth = width / 3;
        List<Renderable> elements = new LinkedList<>();
        elements.add(new GroupByButton(manager, GroupingStrategy.NO_GROUPING, enableButtons, buttonWidth));
        elements.add(new GroupByButton(manager, GroupingStrategy.BY_MARKET, enableButtons, buttonWidth));
        elements.add(new GroupByButton(manager, GroupingStrategy.BY_SYSTEM, enableButtons, buttonWidth));
        return new HorizontalViewContainer(elements);
    }
}
