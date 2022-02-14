package stelnet.board.commodity;

import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.util.Misc;
import java.awt.Color;
import java.util.List;
import lombok.Getter;
import stelnet.BaseIntel;
import stelnet.IntelInfo;
import stelnet.board.commodity.price.DemandPrice;
import stelnet.board.commodity.price.SupplyPrice;
import stelnet.board.commodity.view.intel.IntelViewFactory;
import stelnet.util.L10n;
import stelnet.util.ModConstants;
import uilib.Renderable;
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
        boolean buyChanged = Math.abs(buyPrice - supplyPrice) > 1;
        boolean sellChanged = Math.abs(sellPrice - demandPrice) > 1;
        return buyChanged || sellChanged;
    }

    public String getCommodityId() {
        return commodity.getId();
    }

    @Override
    protected IntelInfo getIntelInfo() {
        return new IntelInfo(
            getTitle(),
            "Buy",
            Misc.getDGSCredits(getSupplyPrice()),
            "Sell",
            Misc.getDGSCredits(getDemandPrice())
        );
    }

    @Override
    public Color getCircleBorderColorOverride() {
        String commodityId = getCommodityId();
        CommodityOnMarketAPI commodityOnMarket = market.getCommodityData(commodityId);
        if (commodityOnMarket.getExcessQuantity() > 0) {
            return Misc.getPositiveHighlightColor();
        }
        if (commodityOnMarket.getDeficitQuantity() > 0) {
            return Misc.getNegativeHighlightColor();
        }
        return Misc.getGrayColor();
    }

    @Override
    protected List<Renderable> getRenderableList(Size size) {
        return (new IntelViewFactory(market, this)).create(size);
    }

    private float getDemandPrice() {
        return (new DemandPrice(commodity.getId())).getPriceAmount(market);
    }

    private float getSupplyPrice() {
        return (new SupplyPrice(commodity.getId())).getPriceAmount(market);
    }

    private String getTitle() {
        return L10n.get(CommodityL10n.INTEL_TITLE, commodity.getName(), market.getName());
    }
}
