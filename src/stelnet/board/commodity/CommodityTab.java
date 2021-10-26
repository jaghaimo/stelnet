package stelnet.board.commodity;

public enum CommodityTab {
    BUY("Buy"),
    SELL("Sell"),
    PROFIT("Profit");

    public final String id;

    private CommodityTab(String id) {
        this.id = id;
    }
}
