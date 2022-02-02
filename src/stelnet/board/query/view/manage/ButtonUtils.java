package stelnet.board.query.view.manage;

import com.fs.starfarer.api.campaign.econ.SubmarketSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.LinkedList;
import java.util.List;
import stelnet.CommonL10n;
import stelnet.board.query.QueryManager;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.board.query.provider.DmodProvider;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.Filter;
import stelnet.filter.FleetMemberHasDMod;
import stelnet.filter.FleetMemberHasDModCount;
import stelnet.filter.ResultIsFriendly;
import stelnet.filter.ResultIsPurchasable;
import uilib.Button;
import uilib.Renderable;

public class ButtonUtils {

    public static FilteringButton[] getDMods(DmodProvider provider) {
        List<FilteringButton> dMods = new LinkedList<>();
        for (HullModSpecAPI dMod : provider.getDMods()) {
            dMods.add(
                new FilteringButton(dMod.getDisplayName(), new FleetMemberHasDMod(dMod.getId(), dMod.getDisplayName()))
            );
        }
        return dMods.toArray(new FilteringButton[] {});
    }

    public static FilteringButton[] getDModsCount() {
        List<FilteringButton> dModCount = new LinkedList<>();
        dModCount.add(new FilteringButton(CommonL10n.NONE, new FleetMemberHasDModCount(0)));
        for (int i = 1; i < 6; i++) {
            dModCount.add(new FilteringButton(String.valueOf(i), new FleetMemberHasDModCount(i)));
        }
        return dModCount.toArray(new FilteringButton[] {});
    }

    public static Button[] getGroupingButtons(QueryManager manager) {
        return new Button[] {
            new GroupByButton(manager, GroupingStrategy.BY_MARKET),
            new GroupByButton(manager, GroupingStrategy.BY_SYSTEM),
        };
    }

    public static Button[] getOtherButtons(QueryManager manager) {
        return new Button[] {
            new SpecialFilterButton(manager, "Only Purchasable Locations", new ResultIsPurchasable()),
            new SpecialFilterButton(manager, "Only Friendly Markets", new ResultIsFriendly()),
        };
    }

    public static Button[] getSubmarketButtons(QueryManager manager) {
        List<Renderable> elements = new LinkedList<>();
        List<SubmarketSpecAPI> allSubmarketSpecs = manager.getSubmarketSpecs();
        for (SubmarketSpecAPI submarketSpec : allSubmarketSpecs) {
            String name = getSubmarketName(submarketSpec);
            Filter filter = manager.getSubmarketFilter(submarketSpec);
            boolean isStateOn = manager.getSubmarketFilters().contains(filter);
            elements.add(new SubmarketFilterButton(manager, name, filter, isStateOn));
        }
        return elements.toArray(new Button[] {});
    }

    private static String getSubmarketName(SubmarketSpecAPI submarketSpec) {
        String name = submarketSpec.getName().replace("\n", " ").trim();
        if (name.isEmpty()) {
            return "Military Market";
        }
        return name;
    }
}
