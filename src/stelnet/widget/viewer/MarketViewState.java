package stelnet.widget.viewer;

public interface MarketViewState {
    public ButtonManager getButtonManager();

    public ContentRenderer getContentRenderer();

    public DisplayStrategy getDisplayStrategy();

    public void setContentRenderer(ContentRenderer renderer);
}
