package stelnet.market.intel.subject;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;

import stelnet.filter.submarket.IsNotLocalResources;
import stelnet.filter.submarket.IsNotStorage;
import stelnet.filter.submarket.NeedsRefresh;
import stelnet.filter.submarket.SubmarketFilter;
import stelnet.helper.CollectionHelper;

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
    public boolean isStale() {
        List<SubmarketAPI> submarkets = market.getSubmarketsCopy();
        CollectionHelper.reduce(
                submarkets,
                Arrays.asList(new IsNotStorage(), new IsNotLocalResources(), new NeedsRefresh())
        );
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
        CollectionHelper.reduce(submarkets, new IsNotStorage());
        return submarkets;
    }

    protected abstract int getEntityCount();

    protected abstract SubmarketFilter getFilter();

    protected abstract int getSubmarketCount();

    protected abstract Set<SubmarketAPI> getSubmarkets();

    protected abstract void populate();
}
