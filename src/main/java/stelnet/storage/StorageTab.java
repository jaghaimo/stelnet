package stelnet.storage;

public enum StorageTab {

    CARGO("Cargo"), SHIPS("Ships");

    public final String title;

    private StorageTab(String title) {
        this.title = title;
    }
}
