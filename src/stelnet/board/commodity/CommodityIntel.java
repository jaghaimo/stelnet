package stelnet.board.commodity;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.List;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import stelnet.EmptyIntel;
import stelnet.board.IntelBasePlugin;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.board.commodity.view.intel.CommodityIntelInfo;
import stelnet.board.commodity.view.intel.CommodityIntelViewFactory;
import stelnet.settings.Modules;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import stelnet.util.StelnetHelper;
import uilib.Renderable;
import uilib.RenderableIntelInfo;
import uilib.property.Size;

@Getter
@Log4j
public class CommodityIntel extends IntelBasePlugin {

    private final String commodityId;
    private final IntelTracker intelTracker;
    private final MarketAPI market;
    private final float buyPrice;
    private final float sellPrice;
    private final String tag = ModConstants.TAG_COMMODITY;

    public CommodityIntel(String commodityId, IntelTracker intelTracker, MarketAPI market) {
        super(market.getFaction(), market.getPrimaryEntity());
        this.commodityId = commodityId;
        this.intelTracker = intelTracker;
        this.market = market;
        this.buyPrice = getSupplyPrice();
        this.sellPrice = getDemandPrice();
    }

    public Object readResolve() {
        if (commodityId == null) {
            log.warn("Commodity is null, removing intel");
            remove();
            return new EmptyIntel();
        }
        if (!StelnetHelper.hasCommodity(commodityId)) {
            log.warn("Commodity " + commodityId + " no longer exists, removing intel");
            remove();
            return new EmptyIntel();
        }
        return this;
    }

    @Override
    public String getIcon() {
        return getCommodity().getIconName();
    }

    @Override
    public boolean isEnding() {
        float supplyPrice = getSupplyPrice();
        float demandPrice = getDemandPrice();
        boolean buyChanged = isDifferent(buyPrice, supplyPrice);
        boolean sellChanged = isDifferent(sellPrice, demandPrice);
        return buyChanged || sellChanged;
    }

    @Override
    public boolean isHidden() {
        return Modules.COMMODITIES.isHidden();
    }

    public CommoditySpecAPI getCommodity() {
        return Global.getSector().getEconomy().getCommoditySpec(commodityId);
    }

    public void remove() {
        intelTracker.remove(this);
    }

    @Override
    protected RenderableIntelInfo getIntelInfo() {
        return new CommodityIntelInfo(this);
    }

    @Override
    public Color getCircleBorderColorOverride() {
        if (isEnding()) {
            return Misc.getGrayColor();
        }
        String commodityId = getCommodityId();
        CommodityOnMarketAPI commodityOnMarket = market.getCommodityData(commodityId);
        if (commodityOnMarket.getExcessQuantity() > 0) {
            return Misc.getPositiveHighlightColor();
        }
        if (commodityOnMarket.getDeficitQuantity() > 0) {
            return Misc.getNegativeHighlightColor();
        }
        return Misc.getTextColor();
    }

    @Override
    protected List<Renderable> getRenderableList(Size size) {
        return (new CommodityIntelViewFactory(market, this)).create(size);
    }

    public float getDemandPrice() {
        return (new DemandPrice(commodityId)).getUnitPrice(market);
    }

    public float getSupplyPrice() {
        return (new SupplyPrice(commodityId)).getUnitPrice(market);
    }

    public String getTitle() {
        return L10n.get(CommodityL10n.INTEL_TITLE, getCommodity().getName(), market.getName());
    }

    public boolean isDifferent(float oldPrice, float newPrice) {
        return Math.abs(oldPrice - newPrice) >= 1;
    }
}
