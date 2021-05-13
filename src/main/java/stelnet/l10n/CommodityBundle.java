package stelnet.l10n;

public class CommodityBundle extends Bundle {

    public CommodityBundle() {
        super("CommodityBundle");
    }

    public String boardTitle() {
        return format("boardTitle");
    }

    public String boardDescription() {
        return format("boardDescription");
    }

    public String intelTitle(Object... objects) {
        return format("intelChanged", objects);
    }

    public String ownerRelationship(String reputation) {
        return format("ownerRelationship", reputation.toLowerCase());
    }

    public String priceChanged(Object... objects) {
        return format("priceChanged", objects);
    }

    public String translateTab(String tab) {
        return format("tab" + tab);
    }
}
