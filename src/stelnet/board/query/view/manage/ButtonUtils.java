package stelnet.board.query.view.manage;

import com.fs.starfarer.api.campaign.econ.SubmarketSpecAPI;
import com.fs.starfarer.api.loading.HullModSpecAPI;
import java.util.LinkedList;
import java.util.List;
import stelnet.board.query.QueryManager;
import stelnet.board.query.grouping.GroupingStrategy;
import stelnet.board.query.provider.DmodProvider;
import stelnet.board.query.view.FilteringButton;
import stelnet.filter.Filter;
import stelnet.filter.ResultFleetMemberWithoutDMod;
import stelnet.filter.ResultFleetMemberWithoutDModCount;
import stelnet.filter.ResultIsFriendly;
import stelnet.filter.ResultIsPurchasable;
import stelnet.util.L10n;
import uilib.Button;
import uilib.Renderable;

public class ButtonUtils {

    public static FilteringButton[] getDMods(final QueryManager manager) {
        final DmodProvider provider = new DmodProvider();
        final List<FilteringButton> dMods = new LinkedList<>();
        for (final HullModSpecAPI dMod : provider.getDMods()) {
            dMods.add(
                new FilterSetAwareButton(
                    manager,
                    dMod.getDisplayName(),
                    new ResultFleetMemberWithoutDMod(dMod.getId()),
                    manager.getDModTypesFilters()
                )
            );
        }
        return dMods.toArray(new FilteringButton[] {});
    }

    public static FilteringButton[] getDModsCount(final QueryManager manager) {
        final List<Button> dModCount = new LinkedList<>();
        dModCount.add(
            new FilterSetAwareButton(
                manager,
                L10n.common("NONE"),
                new ResultFleetMemberWithoutDModCount(0),
                manager.getDModCountFilters()
            )
        );
        for (int i = 1; i < 6; i++) {
            dModCount.add(
                new FilterSetAwareButton(
                    manager,
                    String.valueOf(i),
                    new ResultFleetMemberWithoutDModCount(i),
                    manager.getDModCountFilters()
                )
            );
        }
        return dModCount.toArray(new FilteringButton[] {});
    }

    public static GroupByButton[] getGroupingButtons(final QueryManager manager) {
        return new GroupByButton[] {
            new GroupByButton(manager, GroupingStrategy.BY_MARKET),
            new GroupByButton(manager, GroupingStrategy.BY_SYSTEM),
        };
    }

    public static FilteringButton[] getOtherButtons(final QueryManager manager) {
        return new FilteringButton[] {
            new FilterSetAwareButton(
                manager,
                L10n.query("RESULTS_ONLY_PURCHASABLE"),
                new ResultIsPurchasable(),
                manager.getOtherFilters()
            ),
            new FilterSetAwareButton(
                manager,
                L10n.query("RESULTS_ONLY_FRIENDLY"),
                new ResultIsFriendly(),
                manager.getOtherFilters()
            ),
        };
    }

    public static FilteringButton[] getSubmarketButtons(final QueryManager manager) {
        final List<Renderable> elements = new LinkedList<>();
        final List<SubmarketSpecAPI> allSubmarketSpecs = manager.getSubmarketSpecs();
        for (final SubmarketSpecAPI submarketSpec : allSubmarketSpecs) {
            final String name = getSubmarketName(submarketSpec);
            final Filter filter = manager.getSubmarketFilter(submarketSpec);
            elements.add(new FilterSetAwareButton(manager, name, filter, manager.getSubmarketFilters()));
        }
        return elements.toArray(new FilteringButton[] {});
    }

    private static String getSubmarketName(final SubmarketSpecAPI submarketSpec) {
        final String name = submarketSpec.getName().replace("\n", " ").trim();
        if (name.isEmpty()) {
            return L10n.common("MILITARY_MARKET");
        }
        return name;
    }
}
