package stelnet.market;

public enum QueryTab {

    LIST("List"), NEW("New");

    public final String title;

    private QueryTab(String title) {
        this.title = title;
    }
}
