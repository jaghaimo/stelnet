package stelnet.board.storage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import stelnet.widget.viewer.ContentRenderer;
import stelnet.widget.viewer.DisplayStrategyButton;
import stelnet.widget.viewer.FilteringButtons;
import stelnet.widget.viewer.GroupingStrategy;
import stelnet.widget.viewer.MarketView;
import stelnet.widget.viewer.RendererAwareState;
import uilib.EventHandler;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

@RequiredArgsConstructor
public class StorageView implements RenderableFactory {

    private final FilteringButtons buttonManager;
    private final ContentRenderer activeTab;
    private final GroupingStrategy activeView;
    private final RendererAwareState state;

    public StorageView(StorageState state) {
        this(state.getFilteringButtons(), state.getActiveRenderer(), state.getActiveStrategy(), state);
    }

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> renderables = new LinkedList<>();
        renderables.addAll(new MarketView(buttonManager, activeTab, activeView, state).create(size));
        renderables.add(getNextStrategyButton(size));
        return renderables;
    }

    private Renderable getNextStrategyButton(Size size) {
        final GroupingStrategy nextStrategy = activeView.getNext();
        DisplayStrategyButton button = new DisplayStrategyButton(nextStrategy);
        button.setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    StorageBoard board = StorageBoard.getInstance();
                    board.getState().setActiveStrategy(nextStrategy);
                }
            }
        );
        return button;
    }
}
