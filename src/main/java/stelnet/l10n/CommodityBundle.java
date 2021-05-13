package stelnet.l10n;

public class CommodityBundle extends Bundle {

    public CommodityBundle() {
        super(CommodityBundle.class.getName());
    }

    public String commodityMarket() {
        return format("commodityMarket");
    }
}
