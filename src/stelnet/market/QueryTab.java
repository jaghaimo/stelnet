package stelnet.market;

public enum QueryTab {
    LIST("List"),
    NEW("New");

    public final String id;

    private QueryTab(String id) {
        this.id = id;
    }
}
