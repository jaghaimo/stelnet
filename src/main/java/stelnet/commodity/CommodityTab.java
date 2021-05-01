package stelnet.commodity;

public enum CommodityTab {
    BUY("Buy"), SELL("Sell");

    public String title;

    private CommodityTab(String title) {
        this.title = title;
    }
}
