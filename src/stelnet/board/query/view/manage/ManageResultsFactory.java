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
import stelnet.board.query.view.ButtonGroup;
import stelnet.board.query.view.FilterAwareFactory;
import stelnet.board.query.view.FilteringButton;
import stelnet.board.query.view.SectionHeader;
import stelnet.board.query.view.SizeHelper;
import stelnet.filter.Filter;
import stelnet.filter.ResultIsFriendly;
import stelnet.filter.ResultIsPurchasable;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.UiConstants;
import uilib.property.Size;

@Getter
@RequiredArgsConstructor
public class ManageResultsFactory extends FilterAwareFactory implements RenderableFactory {

    private SizeHelper sizeHelper = new SizeHelper();
    private ButtonHelper buttonHelper = new ButtonHelper(this);

    private final QueryManager manager;
    private final FilteringButton[] dModCount = ButtonUtils.getDModsCount();
    private final FilteringButton[] dModAllowed = ButtonUtils.getDMods(new DmodProvider());

    @Override
    public List<Renderable> create(Size size) {
        sizeHelper = new SizeHelper(size);
        sizeHelper.movePartition(100);
        buttonHelper.prepareDmods();
        List<Renderable> elements = new LinkedList<>();
        elements.add(new Spacer(UiConstants.DEFAULT_BUTTON_HEIGHT));
        elements.addAll(getGroupingButtons(size.getWidth()));
        elements.add(new Spacer(UiConstants.DEFAULT_BUTTON_HEIGHT));
        elements.addAll(getExtraCheckboxes(size.getWidth()));
        elements.add(new Spacer(UiConstants.DEFAULT_BUTTON_HEIGHT));
        elements.addAll(getSubmarketButtons(size.getWidth()));
        elements.add(new SectionHeader(sizeHelper.getGroupAndTextWidth(), QueryL10n.DMODS, true, dModAllowed));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.DMOD_COUNT, dModCount, true));
        elements.add(new ButtonGroup(sizeHelper, QueryL10n.DMOD_SET, dModAllowed));
        return elements;
    }

    public boolean hasDmodSelection() {
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
