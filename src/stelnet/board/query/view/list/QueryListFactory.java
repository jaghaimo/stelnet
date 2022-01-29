package stelnet.board.query.view.list;

import com.fs.starfarer.api.campaign.econ.SubmarketSpecAPI;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.filter.Filter;
import stelnet.filter.ResultIsFriendly;
import stelnet.filter.ResultIsPurchasable;
import stelnet.util.L10n;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.VerticalViewContainer;
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
        addNewQueries(elements, width);
        return elements;
    }

    public RenderableComponent getButtons(Size size) {
        List<Renderable> elements = new LinkedList<>();
        boolean enableButtons = manager.getQueries().size() > 0;
        elements.add(new Spacer(UiConstants.DEFAULT_BUTTON_HEIGHT));
        elements.addAll(getGlobalButtons(enableButtons, size.getWidth()));
        elements.add(new Spacer(UiConstants.DEFAULT_BUTTON_HEIGHT));
        elements.addAll(getGroupingButtons(size.getWidth()));
        elements.add(new Spacer(UiConstants.DEFAULT_BUTTON_HEIGHT));
        elements.addAll(getExtraCheckboxes(size.getWidth()));
        elements.add(new Spacer(UiConstants.DEFAULT_BUTTON_HEIGHT));
        elements.addAll(getSubmarketButtons(size.getWidth()));
        VerticalViewContainer container = new VerticalViewContainer(elements);
        container.setSize(size);
        container.setLocation(Location.TOP_RIGHT);
        return container;
    }

    private void addNewQueries(List<Renderable> elements, float width) {
        if (elements.isEmpty()) {
            elements.add(new Paragraph(L10n.get(QueryL10n.NO_QUERIES), width));
        }
    }

    private List<Renderable> getGlobalButtons(boolean enableButtons, float width) {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new RefreshAllButton(manager, enableButtons, width));
        elements.add(new Spacer(UiConstants.DEFAULT_BUTTON_HEIGHT));
        elements.add(new EnableAllButton(manager, enableButtons, width));
        elements.add(new DisableAllButton(manager, enableButtons, width));
        elements.add(new Spacer(UiConstants.DEFAULT_BUTTON_HEIGHT));
        elements.add(new DeleteAllButton(manager, enableButtons, width));
        return elements;
    }

    private List<Renderable> getGroupingButtons(float width) {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new GroupByButton(manager, GroupingStrategy.BY_MARKET, width));
        elements.add(new GroupByButton(manager, GroupingStrategy.BY_SYSTEM, width));
        return elements;
    }

    private List<Renderable> getExtraCheckboxes(float width) {
        List<Renderable> elements = new LinkedList<>();
        elements.add(new SpecialFilterButton(manager, "Only Purchasable Locations", new ResultIsPurchasable(), width));
        elements.add(new SpecialFilterButton(manager, "Only Friendly Markets", new ResultIsFriendly(), width));
        return elements;
    }

    private List<Renderable> getSubmarketButtons(float width) {
        List<Renderable> elements = new LinkedList<>();
        List<SubmarketSpecAPI> allSubmarketSpecs = manager.getSubmarketSpecs();
        for (SubmarketSpecAPI submarketSpec : allSubmarketSpecs) {
            String name = getSubmarketName(submarketSpec);
            Filter filter = manager.getSubmarketFilter(submarketSpec);
            boolean isStateOn = manager.getSubmarketFilters().contains(filter);
            elements.add(new SubmarketFilterButton(manager, name, filter, width, isStateOn));
        }
        return elements;
    }

    private String getSubmarketName(SubmarketSpecAPI submarketSpec) {
        String name = submarketSpec.getName().replace("\n", " ").trim();
        if (name.isEmpty()) {
            return "Military Market";
        }
        return name;
    }
}
