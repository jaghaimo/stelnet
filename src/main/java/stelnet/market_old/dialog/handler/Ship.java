package stelnet.market_old.dialog.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fs.starfarer.api.FactoryAPI;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.FleetMemberPickerListener;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.fleet.FleetMemberType;

import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.helper.CollectionHelper;
import stelnet.helper.MarketHelper;
import stelnet.market_old.dialog.DialogOption;
import stelnet.market_old.dialog.DialogPlugin;
import stelnet.market_old.filter.MutableFilterManager;
import stelnet.market_old.intel.provider.IntelProvider;
import stelnet.market_old.intel.provider.ShipIntelProvider;
import stelnet.market_old.intel.subject.ShipSubject;

public class Ship extends FilterAware implements FleetMemberPickerListener {

    private DialogPlugin plugin;

    public Ship(DialogOption o, DialogOption p) {
        super(o, p);
    }

    @Override
    public void cancelledFleetMemberPicking() {
        plugin.addText("Query cancelled...");
    }

    @Override
    public void pickedFleetMembers(List<FleetMemberAPI> fleet) {
        if (fleet.isEmpty()) {
            plugin.addText("Query cancelled...");
            return;
        }
        for (FleetMemberAPI fleetMember : fleet) {
            ShipSubject subject = new ShipSubject(fleetMember, null);
            IntelProvider provider = new ShipIntelProvider(fleetMember);
            plugin.addText("Adding intel query for " + subject.getIntelTitle() + ".");
            plugin.addNewQuery(provider);
        }
        Menu.forceMenu(plugin);
    }

    @Override
    protected DialogOption run(DialogPlugin plugin) {
        this.plugin = plugin;
        MutableFilterManager filterManager = plugin.getFilterManager();
        List<FleetMemberAPI> fleet = findShips(filterManager);

        if (fleet.isEmpty()) {
            String size = filterManager.getFleetShipSize().name().substring(10).toLowerCase();
            plugin.addText("No markets selling specified " + size + "s found.");
            return option;
        }

        plugin.getDialog().showFleetMemberPickerDialog("Pick ships to query for...", "Query", "Cancel", 8, 12, 64f,
                true, true, fleet, this);
        return option;
    }

    private List<FleetMemberAPI> findShips(MutableFilterManager filterManager) {
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets();
        List<FleetMemberFilter> fleetMemberFilters = filterManager.listFleetFilters();
        Map<String, FleetMemberAPI> fleetMap = new HashMap<>();

        for (SubmarketAPI s : submarkets) {
            List<FleetMemberAPI> submarketShips = s.getCargo().getMothballedShips().getMembersListCopy();
            CollectionHelper.reduce(submarketShips, fleetMemberFilters);
            addAllShipHulls(fleetMap, submarketShips);
        }

        List<FleetMemberAPI> fleetMembers = new ArrayList<>(fleetMap.values());
        Collections.sort(fleetMembers, new FleetComparator());

        return fleetMembers;
    }

    private void addAllShipHulls(Map<String, FleetMemberAPI> fleetMap, List<FleetMemberAPI> shipList) {
        FactoryAPI factory = Global.getFactory();
        for (FleetMemberAPI ship : shipList) {
            String hullId = ship.getHullId();
            String baseHullId = ship.getHullSpec().getBaseHullId();
            FleetMemberAPI member = factory.createFleetMember(FleetMemberType.SHIP, baseHullId + "_Hull");
            member.setVariant(ship.getVariant(), false, false);
            fleetMap.put(hullId, member);
        }
    }

    private static class FleetComparator implements Comparator<FleetMemberAPI> {

        @Override
        public int compare(FleetMemberAPI f1, FleetMemberAPI f2) {
            String f1Name = f1.getHullSpec().getHullName();
            String f2Name = f2.getHullSpec().getHullName();

            return f1Name.compareTo(f2Name);
        }
    }
}
