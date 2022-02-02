package stelnet.board.query.view.manage;

import com.fs.starfarer.api.campaign.econ.SubmarketSpecAPI;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.QueryL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.board.query.provider.DmodProvider;
import stelnet.board.query.view.FilterAwareFactory;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.Filter;
import stelnet.filter.LogicalAnd;
import stelnet.filter.LogicalOr;
import stelnet.filter.ResultIsFriendly;
import stelnet.filter.ResultIsPurchasable;
import stelnet.util.L10n;
import uilib.Renderable;
import uilib.RenderableComponent;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.VerticalViewContainer;
import uilib.property.Location;
import uilib.property.Size;

@Getter
@RequiredArgsConstructor
public class ManageResultsFactory extends FilterAwareFactory implements RenderableFactory {

    private final QueryManager manager;

    private final FilteringButton[] dModCount = ButtonUtils.getDModsCount();
    private final FilteringButton[] dModAllowed = ButtonUtils.getDMods(new DmodProvider());

    @Override
    public List<Renderable> create(Size size) {
        return null;
    }

    public void addDmodFilters(Set<Filter> filters) {
        addSelectedOrNone(filters, dModCount, L10n.get(QueryL10n.DMOD_COUNT), true);
        Set<Filter> allowedDmods = getFilters(dModAllowed, true);
        Set<Filter> disallowedDmods = getFilters(dModAllowed, false);
        if (!hasDmodSelection()) {
            return;
        }
        filters.add(new LogicalOr(allowedDmods, L10n.get(QueryL10n.DMOD_ALLOWED)));
        filters.add(new LogicalAnd(negateFilters(disallowedDmods), L10n.get(QueryL10n.DMOD_DISALLOWED)));
    }

    public RenderableComponent getButtons(Size size) {
        List<Renderable> elements = new LinkedList<>();
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

    protected boolean hasDmodSelection() {
        Set<Filter> allowedDmods = getFilters(dModAllowed, true);
        Set<Filter> disallowedDmods = getFilters(dModAllowed, false);
        return !allowedDmods.isEmpty() && !disallowedDmods.isEmpty();
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
