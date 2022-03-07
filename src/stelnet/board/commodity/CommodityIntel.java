package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.board.commodity.view.intel.CommodityIntelInfo;
import stelnet.board.commodity.view.intel.CommodityIntelViewFactory;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import uilib.Renderable;
import uilib.RenderableIntelInfo;
import uilib.property.Size;

@Getter
public class CommodityIntel extends BaseIntel {

    private final CommoditySpecAPI commodity;
    private final MarketAPI market;
    private final float buyPrice;
    private final float sellPrice;
    private final String tag = ModConstants.TAG_COMMODITY;

    public CommodityIntel(CommoditySpecAPI commodity, MarketAPI market) {
        super(market.getFaction(), market.getPrimaryEntity());
        this.commodity = commodity;
        this.market = market;
        this.buyPrice = getSupplyPrice();
        this.sellPrice = getDemandPrice();
    }

    @Override
    public String getIcon() {
        return commodity.getIconName();
    }

    @Override
    public boolean isEnding() {
        float supplyPrice = getSupplyPrice();
        float demandPrice = getDemandPrice();
        boolean buyChanged = isDifferent(buyPrice, supplyPrice);
        boolean sellChanged = isDifferent(sellPrice, demandPrice);
        return buyChanged || sellChanged;
    }

    public String getCommodityId() {
        return commodity.getId();
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
        return (new DemandPrice(commodity.getId())).getPriceAmount(market);
    }

    public float getSupplyPrice() {
        return (new SupplyPrice(commodity.getId())).getPriceAmount(market);
    }

    public String getTitle() {
        return L10n.get(CommodityL10n.INTEL_TITLE, commodity.getName(), market.getName());
    }

    public boolean isDifferent(float oldPrice, float newPrice) {
        return Math.abs(oldPrice - newPrice) >= 1;
    }
}
