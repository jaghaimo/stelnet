package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoPickerListener;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FleetMemberPickerListener;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import stelnet.board.query.Query;
import stelnet.board.query.QueryBoard;
import stelnet.board.query.QueryManager;
import stelnet.board.query.QueryState;
import stelnet.board.query.QueryState.QueryBoardTab;
import stelnet.board.query.provider.QueryProvider;
import stelnet.board.query.view.add.QueryFactory;
import stelnet.filter.CargoStackIsStack;
import stelnet.filter.Filter;
import stelnet.filter.LogicalOr;
import stelnet.filter.ShipHullIsHull;
import stelnet.util.L10n;
import stelnet.util.StelnetHelper;

@RequiredArgsConstructor
public class QueryPickerListener implements CargoPickerListener, FleetMemberPickerListener {

    private final PickerDialog dialog;
    private final QueryFactory factory;
    private final String type;

    @Override
    public void pickedCargo(final CargoAPI cargo) {
        final Set<Filter> selectedFilters = new LinkedHashSet<>();
        cargo.sort();
        for (final CargoStackAPI cargoStack : cargo.getStacksCopy()) {
            selectedFilters.add(new CargoStackIsStack(cargoStack));
        }
        addQuery(selectedFilters, L10n.common("ITEMS"));
    }

    @Override
    public void pickedFleetMembers(final List<FleetMemberAPI> members) {
        final Set<Filter> selectedFilters = new LinkedHashSet<>();
        for (final FleetMemberAPI member : members) {
            selectedFilters.add(new ShipHullIsHull(member.getHullSpec()));
        }
        addQuery(selectedFilters, L10n.common("SHIPS"));
    }

    @Override
    public void cancelledCargoSelection() {
        dialog.dismiss(null);
    }

    @Override
    public void cancelledFleetMemberPicking() {
        dialog.dismiss(null);
    }

    @Override
    public void recreateTextPanel(
        final TooltipMakerAPI panel,
        final CargoAPI cargo,
        final CargoStackAPI pickedUp,
        final boolean pickedUpFromSource,
        final CargoAPI combined
    ) {}

    private void addQuery(final Set<Filter> selectedFilters, final String label) {
        final Set<Filter> filters = new LinkedHashSet<>();
        filters.add(new LogicalOr(selectedFilters, label));
        final QueryBoard board = StelnetHelper.getInstance(QueryBoard.class);
        final QueryState state = board.getState();
        final QueryManager manager = state.getQueryManager();
        final QueryProvider provider = factory.getProvider();
        final Query query = new Query(manager, provider, filters, type);
        manager.addQuery(query);
        state.setActiveTab(QueryBoardTab.LIST);
        dialog.dismiss(board);
    }
}
