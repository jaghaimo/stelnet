package stelnet.storage;

import java.util.ArrayList;
import java.util.List;

import stelnet.filter.cargostack.CargoStackFilter;
import stelnet.filter.fleetmember.FleetMemberFilter;
import stelnet.storage.button.Button;
import stelnet.storage.button.ButtonManager;

public class FilterFactory {

    private ButtonManager buttonManager;

    public FilterFactory(ButtonManager buttonManager) {
        this.buttonManager = buttonManager;
    }

    public List<CargoStackFilter> getCargoStackFilters() {
        List<CargoStackFilter> filters = new ArrayList<>();
        List<Object> objects = extractFilters(buttonManager.getAllCargoButtons());
        filters.addAll(convertToCargoStackFilters(objects));
        return filters;
    }

    public List<FleetMemberFilter> getFleetMemberFilters() {
        List<FleetMemberFilter> filters = new ArrayList<>();
        List<Object> objects = extractFilters(buttonManager.getAllShipButtons());
        filters.addAll(convertToFleetMemberFilters(objects));
        return filters;
    }

    private List<CargoStackFilter> convertToCargoStackFilters(List<Object> objects) {
        List<CargoStackFilter> filters = new ArrayList<>();
        for (Object object : objects) {
            filters.add((CargoStackFilter) object);
        }
        return filters;
    }

    private List<FleetMemberFilter> convertToFleetMemberFilters(List<Object> objects) {
        List<FleetMemberFilter> filters = new ArrayList<>();
        for (Object object : objects) {
            filters.add((FleetMemberFilter) object);
        }
        return filters;
    }

    private List<Object> extractFilters(List<Button> buttons) {
        List<Object> filters = new ArrayList<>();
        for (Button button : buttons) {
            Object filter = button.getFilter();
            if (filter == null) {
                continue;
            }
            filters.add(filter);
        }
        return filters;
    }
}
