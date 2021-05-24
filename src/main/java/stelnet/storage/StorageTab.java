package stelnet.storage;

public enum StorageTab {

    ITEMS("Items"), SHIPS("Ships");

    public final String title;

    private StorageTab(String title) {
        this.title = title;
    }
}
