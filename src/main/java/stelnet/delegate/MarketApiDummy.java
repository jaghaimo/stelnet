package stelnet.delegate;

import java.awt.Color;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.CommDirectoryAPI;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.FactionAPI.ShipPickParams;
import com.fs.starfarer.api.campaign.LocationAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.campaign.econ.MarketDemandAPI;
import com.fs.starfarer.api.campaign.econ.MarketDemandDataAPI;
import com.fs.starfarer.api.campaign.econ.MarketImmigrationModifier;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.combat.MutableStatWithTempMods;
import com.fs.starfarer.api.combat.StatBonus;
import com.fs.starfarer.api.fleet.MutableMarketStatsAPI;
import com.fs.starfarer.api.fleet.ShipFilter;
import com.fs.starfarer.api.fleet.ShipRolePick;
import com.fs.starfarer.api.impl.campaign.econ.impl.ConstructionQueue;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;

import org.lwjgl.util.vector.Vector2f;

public class MarketApiDummy implements MarketAPI {

    @Override
    public SectorEntityToken getPrimaryEntity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<SectorEntityToken> getConnectedEntities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setSize(int size) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<CommodityOnMarketAPI> getAllCommodities() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CommodityOnMarketAPI getCommodityData(String commodityId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CommodityOnMarketAPI> getCommoditiesWithTag(String tag) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CommodityOnMarketAPI> getCommoditiesWithTags(String... tags) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MarketDemandAPI getDemand(String demandClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MarketDemandAPI> getDemandWithTag(String tag) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MarketConditionAPI> getConditions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addCondition(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addCondition(String id, Object param) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeCondition(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeSpecificCondition(String token) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasCondition(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasSpecificCondition(String token) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void reapplyConditions() {
        // TODO Auto-generated method stub

    }

    @Override
    public void reapplyCondition(String token) {
        // TODO Auto-generated method stub

    }

    @Override
    public MarketDemandDataAPI getDemandData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableStat getTariff() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StatBonus getDemandPriceMod() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StatBonus getSupplyPriceMod() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getSupplyPrice(String commodityId, double quantity, boolean isPlayerPrice) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getDemandPrice(String commodityId, double quantity, boolean isPlayerPrice) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getDemandPriceAssumingExistingTransaction(String commodityId, double quantity,
            double existingTransactionUtility, boolean isPlayerPrice) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getSupplyPriceAssumingExistingTransaction(String commodityId, double quantity,
            double existingTransactionUtility, boolean isPlayerPrice) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isIllegal(String commodityId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isIllegal(CommodityOnMarketAPI com) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public MutableStatWithTempMods getStability() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getStabilityValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public FactionAPI getFaction() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getFactionId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addSubmarket(String specId) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasSubmarket(String specId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<SubmarketAPI> getSubmarketsCopy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeSubmarket(String specId) {
        // TODO Auto-generated method stub

    }

    @Override
    public SubmarketAPI getSubmarket(String specId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setFactionId(String factionId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePriceMult() {
        // TODO Auto-generated method stub

    }

    @Override
    public MemoryAPI getMemory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MemoryAPI getMemoryWithoutUpdate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float pickShipAndAddToFleet(String role, ShipPickParams params, CampaignFleetAPI fleet) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float pickShipAndAddToFleet(String role, String factionId, ShipPickParams params, CampaignFleetAPI fleet) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getShipQualityFactor() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public StarSystemAPI getStarSystem() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LocationAPI getContainingLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Vector2f getLocationInHyperspace() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPrimaryEntity(SectorEntityToken primaryEntity) {
        // TODO Auto-generated method stub

    }

    @Override
    public CommDirectoryAPI getCommDirectory() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addPerson(PersonAPI person) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removePerson(PersonAPI person) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<PersonAPI> getPeopleCopy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableMarketStatsAPI getStats() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ShipRolePick> pickShipsForRole(String role, ShipPickParams params, Random random, ShipFilter filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ShipRolePick> pickShipsForRole(String role, String factionId, ShipPickParams params, Random random,
            ShipFilter filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isPlanetConditionMarketOnly() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setPlanetConditionMarketOnly(boolean isPlanetConditionMarketOnly) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setName(String name) {
        // TODO Auto-generated method stub

    }

    @Override
    public MutableStat getHazard() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getHazardValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public PlanetAPI getPlanetEntity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SurveyLevel getSurveyLevel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setSurveyLevel(SurveyLevel surveyLevel) {
        // TODO Auto-generated method stub

    }

    @Override
    public void advance(float amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isForceNoConvertOnSave() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setForceNoConvertOnSave(boolean forceNoConvertOnSave) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePrices() {
        // TODO Auto-generated method stub

    }

    @Override
    public MarketConditionAPI getSpecificCondition(String token) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MarketConditionAPI getFirstCondition(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isInEconomy() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Industry> getIndustries() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addIndustry(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeIndustry(String id, MarketInteractionMode mode, boolean forUpgrade) {
        // TODO Auto-generated method stub

    }

    @Override
    public void reapplyIndustries() {
        // TODO Auto-generated method stub

    }

    @Override
    public Vector2f getLocation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Industry getIndustry(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasIndustry(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<CommodityOnMarketAPI> getCommoditiesCopy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MarketConditionAPI getCondition(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getIndustryUpkeep() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getIndustryIncome() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean hasWaystation() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Industry instantiateIndustry(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MarketAPI clone() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clearCommodities() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isPlayerOwned() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setPlayerOwned(boolean playerOwned) {
        // TODO Auto-generated method stub

    }

    @Override
    public float getPrevStability() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getExportIncome(boolean withOverhead) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public float getNetIncome() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public MutableStat getIncomeMult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MutableStat getUpkeepMult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PopulationComposition getPopulation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PopulationComposition getIncoming() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPopulation(PopulationComposition population) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setIncoming(PopulationComposition incoming) {
        // TODO Auto-generated method stub

    }

    @Override
    public LinkedHashSet<MarketImmigrationModifier> getImmigrationModifiers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LinkedHashSet<MarketImmigrationModifier> getTransientImmigrationModifiers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addImmigrationModifier(MarketImmigrationModifier mod) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeImmigrationModifier(MarketImmigrationModifier mod) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addTransientImmigrationModifier(MarketImmigrationModifier mod) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeTransientImmigrationModifier(MarketImmigrationModifier mod) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<MarketImmigrationModifier> getAllImmigrationModifiers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public float getIncentiveCredits() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setIncentiveCredits(float incentiveCredits) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isImmigrationIncentivesOn() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setImmigrationIncentivesOn(Boolean incentivesOn) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isFreePort() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setFreePort(boolean freePort) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isImmigrationClosed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setImmigrationClosed(boolean closed) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean wasIncomingSetBefore() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void addCondition(MarketConditionAPI mc) {
        // TODO Auto-generated method stub

    }

    @Override
    public PersonAPI getAdmin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAdmin(PersonAPI admin) {
        // TODO Auto-generated method stub

    }

    @Override
    public float getDaysInExistence() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setDaysInExistence(float daysInExistence) {
        // TODO Auto-generated method stub

    }

    @Override
    public StatBonus getAccessibilityMod() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean hasSpaceport() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setHasSpaceport(boolean hasSpaceport) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setHasWaystation(boolean hasWaystation) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getEconGroup() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setEconGroup(String econGroup) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addIndustry(String id, List<String> params) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean hasTag(String tag) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void addTag(String tag) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeTag(String tag) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<String> getTags() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clearTags() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getOnOrAt() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Color getTextColorForFactionOrPlanet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Color getDarkColorForFactionOrPlanet() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isHidden() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setHidden(Boolean hidden) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isUseStockpilesForShortages() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setUseStockpilesForShortages(boolean useStockpilesForShortages) {
        // TODO Auto-generated method stub

    }

    @Override
    public float getShortageCounteringCost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void addSubmarket(SubmarketAPI submarket) {
        // TODO Auto-generated method stub

    }

    @Override
    public ConstructionQueue getConstructionQueue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isInHyperspace() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public LinkedHashSet<String> getSuppressedConditions() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isConditionSuppressed(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void suppressCondition(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unsuppressCondition(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public float getImmigrationIncentivesCost() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isInvalidMissionTarget() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setInvalidMissionTarget(Boolean invalidMissionTarget) {
        // TODO Auto-generated method stub

    }

}
