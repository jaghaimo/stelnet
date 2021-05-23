package stelnet.storage;

public enum StorageView {

    PER_LOCATION("PerLocation"), UNIFIED("Unified");

    public final String title;

    private StorageView(String title) {
        this.title = title;
    }
}
