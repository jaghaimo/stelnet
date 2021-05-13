package stelnet.l10n;

public class CommodityBundle extends Bundle {

    public String boardTitle() {
        return format("commodityBoardTitle");
    }

    public String boardDescription() {
        return format("commodityBoardDescription");
    }

    public String intelTitle(Object... objects) {
        return format("commodityIntelTitle", objects);
    }

    public String ownerRelationship(String reputation) {
        return format("commodityOwnerRelationship", reputation.toLowerCase());
    }

    public String priceChanged(Object... objects) {
        return format("commodityPriceChanged", objects);
    }

    public String translateTab(String tab) {
        return format("commodityTab" + tab);
    }

    public String translateHeader(String column) {
        return format("commodityHeader" + column);
    }
}
