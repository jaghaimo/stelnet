package stelnet.widget.viewer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.lwjgl.input.Keyboard;
import stelnet.util.L10n;
import uilib.Group;
import uilib.Heading;
import uilib.HorizontalViewContainer;
import uilib.Paragraph;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.Spacer;
import uilib.TabButton;
import uilib.TabViewContainer;
import uilib.property.Position;
import uilib.property.Size;

public class MarketView implements RenderableFactory {

    private final ButtonManager buttonManager;
    private final ContentRenderer activeTab;
    private final DisplayStrategy activeView;
    private final MarketViewState state;

    public MarketView(final MarketViewState state) {
        buttonManager = state.getButtonManager();
        activeTab = state.getContentRenderer();
        activeView = state.getDisplayStrategy();
        this.state = state;
    }

    @Override
    public List<Renderable> create(final Size size) {
        return Collections.<Renderable>singletonList(getTabViewContainer(size));
    }

    protected boolean isActive(final ContentRenderer currentTab) {
        return currentTab.equals(activeTab);
    }

    protected TabButton getTabButton(final ContentRenderer currentTab, final int keyboardShortcut) {
        return new ViewerTabButton(currentTab, state, isActive(currentTab), keyboardShortcut);
    }

    private Renderable getTabViewContainer(final Size size) {
        final float width = size.getWidth() - 210;
        final float height = size.getHeight() - 40;
        final Size contentSize = new Size(width, height);

        final TabViewContainer tabViewContainer = new TabViewContainer();
        tabViewContainer.setSize(size);
        tabViewContainer.addTab(
            getTabButton(ContentRenderer.ITEMS, Keyboard.KEY_I),
            getTabPane(size, contentSize, buttonManager.getItemButtons()),
            isActive(ContentRenderer.ITEMS)
        );
        tabViewContainer.addTab(
            getTabButton(ContentRenderer.SHIPS, Keyboard.KEY_S),
            getTabPane(size, contentSize, buttonManager.getShipButtons()),
            isActive(ContentRenderer.SHIPS)
        );

        return tabViewContainer;
    }

    private Renderable getTabPane(final Size size, final Size contentSize, final Renderable[] buttons) {
        final List<Renderable> elements = new ArrayList<>();
        final List<LocationContent> storageData = activeView.getData(buttonManager);
        addEmptyData(elements, storageData, contentSize.getWidth());
        addStorageData(elements, storageData);
        final Group contentContainer = new Group(elements);
        contentContainer.setSize(contentSize);
        final Group buttonContainer = new Group(buttons);
        buttonContainer.setSize(new Size(size.reduce(contentSize).getWidth(), contentSize.getHeight()));
        buttonContainer.setOffset(new Position(8, 0));
        return new HorizontalViewContainer(contentContainer, buttonContainer);
    }

    private void addEmptyData(
        final List<Renderable> elements,
        final List<LocationContent> storageData,
        final float width
    ) {
        if (storageData.isEmpty()) {
            elements.add(new Paragraph(L10n.widget("VIEWER_NO_STORAGES"), width));
        }
    }

    private void addStorageData(final List<Renderable> elements, final List<LocationContent> storageData) {
        for (final LocationContent data : storageData) {
            final LocationInfo locationData = data.getLocationInfo();
            elements.add(new Heading(locationData.getName(), locationData.getFgColor(), locationData.getBgColor()));
            elements.add(activeTab.getStorageRenderer(data));
            elements.add(new Spacer(8));
        }
        removeLastElement(elements);
    }

    private void removeLastElement(final List<Renderable> elements) {
        if (elements.size() > 0) {
            elements.remove(elements.size() - 1);
        }
    }
}
