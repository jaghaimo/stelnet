package stelnet.board.query.view.dialog;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoPickerListener;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FleetMemberPickerListener;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import java.util.Collections;
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
import stelnet.filter.ShipHullIsHull;

@RequiredArgsConstructor
public class QueryPickerListener implements CargoPickerListener, FleetMemberPickerListener {

    private final PickerDialog dialog;
    private final QueryFactory factory;

    @Override
    public void pickedCargo(CargoAPI cargo) {
        Set<Filter> newFilters = new LinkedHashSet<>();
        cargo.sort();
        for (CargoStackAPI cargoStack : cargo.getStacksCopy()) {
            newFilters.add(new CargoStackIsStack(cargoStack));
        }
        addQuery(newFilters);
    }

    @Override
    public void pickedFleetMembers(List<FleetMemberAPI> members) {
        Set<Filter> newFilters = new LinkedHashSet<>();
        for (FleetMemberAPI member : members) {
            newFilters.add(new ShipHullIsHull(member.getHullSpec()));
        }
        addQuery(newFilters);
    }

    @Override
    public void cancelledCargoSelection() {
        addQuery(Collections.<Filter>emptySet());
    }

    @Override
    public void cancelledFleetMemberPicking() {
        addQuery(Collections.<Filter>emptySet());
    }

    @Override
    public void recreateTextPanel(
        TooltipMakerAPI panel,
        CargoAPI cargo,
        CargoStackAPI pickedUp,
        boolean pickedUpFromSource,
        CargoAPI combined
    ) {}

    private void addQuery(Set<Filter> newFilters) {
        QueryState state = QueryBoard.getInstance(QueryBoard.class).getState();
        QueryManager manager = state.getQueryManager();
        QueryProvider provider = factory.getProvider();
        Set<Filter> filters = factory.getFilters();
        filters.addAll(newFilters);
        Query query = new Query(manager, provider, filters);
        manager.addQuery(query);
        state.setActiveTab(QueryBoardTab.LIST);
        dialog.dismiss();
    }
}
