package stelnet.commodity;

import stelnet.l10n.CommodityBundle;

public enum CommodityTab {
    BUY("Buy"), SELL("Sell"), PROFIT("Profit");

    private String title;

    private CommodityTab(String title) {
        this.title = title;
    }

    public String getTitle() {
        CommodityBundle bundle = new CommodityBundle();
        return bundle.translateTab(title);
    }
}
