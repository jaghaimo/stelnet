package stelnet.market.dialog.handler;

import java.util.ArrayList;
import java.util.List;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.CargoPickerListener;
import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.helper.CargoHelper;
import stelnet.helper.CollectionHelper;
import stelnet.helper.MarketHelper;
import stelnet.market.dialog.DialogOption;
import stelnet.market.dialog.DialogPlugin;
import stelnet.market.filter.MutableFilterManager;
import stelnet.market.intel.provider.CargoIntelProvider;
import stelnet.market.intel.provider.IntelProvider;

public class Cargo extends FilterAware implements CargoPickerListener {

    private DialogPlugin plugin;

    public Cargo(DialogOption o, DialogOption p) {
        super(o, p);
    }

    @Override
    public void cancelledCargoSelection() {
        plugin.addText("Query cancelled...");
    }

    @Override
    public void pickedCargo(CargoAPI cargo) {
        if (cargo.isEmpty()) {
            plugin.addText("Query cancelled...");
            return;
        }
        cargo.sort();
        for (CargoStackAPI cargoStack : cargo.getStacksCopy()) {
            IntelProvider provider = new CargoIntelProvider(cargoStack);
            plugin.addText("Adding intel query for " + cargoStack.getDisplayName() + ".");
            plugin.addNewQuery(provider);
        }
        Menu.forceMenu(plugin);
    }

    @Override
    public void recreateTextPanel(TooltipMakerAPI panel, CargoAPI cargo, CargoStackAPI pickedUp,
            boolean pickedUpFromSource, CargoAPI combined) {
    }

    @Override
    protected DialogOption run(DialogPlugin plugin) {
        this.plugin = plugin;
        MutableFilterManager filterManager = plugin.getFilterManager();
        CargoAPI cargo = getCargo(filterManager);

        if (cargo.isEmpty()) {
            String category = filterManager.getCargoType().getName().substring(7).toLowerCase();
            plugin.addText("No markets selling " + category + " found.");
            return option;
        }

        plugin.getDialog().showCargoPickerDialog("Pick items to query for...", "Query", "Cancel", false, 0f, cargo,
                this);
        return option;
    }

    private CargoAPI getCargo(MutableFilterManager filterManager) {
        List<CargoStackAPI> cargoStacks = findItems(filterManager);
        return makeCargoFromStacks(cargoStacks);
    }

    private List<CargoStackAPI> findItems(MutableFilterManager filterManager) {
        List<SubmarketAPI> submarkets = MarketHelper.getSubmarkets();
        List<CargoStackFilter> cargoStackFilters = filterManager.listCargoFilters();
        List<CargoStackAPI> cargoStacks = new ArrayList<CargoStackAPI>();

        for (SubmarketAPI s : submarkets) {
            List<CargoStackAPI> submarketStacks = s.getCargo().getStacksCopy();
            CollectionHelper.reduce(submarketStacks, cargoStackFilters);
            cargoStacks.addAll(submarketStacks);
        }

        return cargoStacks;
    }

    private CargoAPI makeCargoFromStacks(List<CargoStackAPI> cargoStacks) {
        CargoAPI cargo = CargoHelper.createCargo(true);
        for (CargoStackAPI cargoStack : cargoStacks) {
            cargo.addFromStack(cargoStack);
        }
        cargo.sort();
        return cargo;
    }
}
