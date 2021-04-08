package stelnet.market.subject;

import java.util.List;
import java.util.Set;

import com.fs.starfarer.api.campaign.CargoStackAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.fleet.FleetMemberAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.filter.cargostack.HasStack;
import stelnet.filter.fleetmember.HasMember;
import stelnet.filter.submarket.SubmarketFilter;
import stelnet.helper.CollectionHelper;
import stelnet.market.IntelSubject;

public abstract class SubmarketSubject extends IntelSubject {

    public SubmarketSubject(String e, MarketAPI m) {
        super(e, m);
    }

    @Override
    public void createSmallDescription(TooltipMakerAPI info, float width, float height) {
        populate();
        addHeader(info, width);
        addBasicInfo(info);
        for (SubmarketAPI submarket : getSubmarkets()) {
            addSubmarket(info, submarket);
        }
    }

    @Override
    public boolean isAvailable() {
        SubmarketFilter filter = getFilter();
        List<SubmarketAPI> submarkets = market.getSubmarketsCopy();
        CollectionHelper.reduce(submarkets, filter);
        return !submarkets.isEmpty();
    }

    protected void addBasicInfo(TooltipMakerAPI info) {
        int submarketCount = getSubmarketCount();
        String isOrAreSubmarkets = submarketCount == 1 ? "is" : "are";
        String submarketOrSubmarkets = submarketCount == 1 ? "" : "s";

        int entityCount = getEntityCount();
        String entityOrEntities = entityCount == 1 ? "" : "s";

        String basicInfo = String.format("There %s %d submarket%s with a total of %d %s%s on %s. ", isOrAreSubmarkets,
                submarketCount, submarketOrSubmarkets, entityCount, entity, entityOrEntities, market.getName());
        super.addBasicInfo(info, basicInfo);
    }

    protected void addSubmarket(TooltipMakerAPI info, SubmarketAPI submarket) {
        FactionAPI faction = submarket.getFaction();
        info.addPara("", 0f);
        info.addPara(submarket.getNameOneLine(), faction.getBaseUIColor(), 10f);
    }

    protected List<SubmarketAPI> findSubmarkets() {
        List<SubmarketAPI> submarkets = market.getSubmarketsCopy();
        CollectionHelper.reduce(submarkets, getFilter());
        return submarkets;
    }

    protected CargoStackAPI getCargoStack(SubmarketAPI submarket, CargoStackAPI cargoStack) {
        List<CargoStackAPI> cargoStacks = submarket.getCargo().getStacksCopy();
        CollectionHelper.reduce(cargoStacks, new HasStack(cargoStack));
        if (cargoStacks.isEmpty()) {
            return cargoStack;
        }
        return cargoStacks.get(0);
    }

    protected List<FleetMemberAPI> getFleetMembers(SubmarketAPI submarket, FleetMemberAPI fleetMember) {
        List<FleetMemberAPI> fleetMembers = submarket.getCargo().getMothballedShips().getMembersListCopy();
        CollectionHelper.reduce(fleetMembers, new HasMember(fleetMember));
        return fleetMembers;
    }

    protected abstract int getEntityCount();

    protected abstract SubmarketFilter getFilter();

    protected abstract int getSubmarketCount();

    protected abstract Set<SubmarketAPI> getSubmarkets();

    protected abstract void populate();
}
