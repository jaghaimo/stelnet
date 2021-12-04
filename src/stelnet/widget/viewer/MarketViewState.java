package stelnet.widget.viewer;

public interface MarketViewState {
    public ButtonManager getFilteringButtons();

    public ContentRenderer getContentRenderer();

    public DisplayStrategy getDisplayStrategy();

    public void setContentRenderer(ContentRenderer renderer);
}
