package stelnet.commodity;

public enum CommodityTab {
    BUY("Buy"), SELL("Sell"), PROFIT("Profit");

    public String title;

    private CommodityTab(String title) {
        this.title = title;
    }
}
