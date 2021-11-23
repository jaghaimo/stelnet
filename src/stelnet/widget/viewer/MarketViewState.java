package stelnet.widget.viewer;

public interface MarketViewState {
    public FilteringButtons getFilteringButtons();

    public ContentRenderer getContentRenderer();

    public DisplayStrategy getDisplayStrategy();

    public void setContentRenderer(ContentRenderer renderer);
}
