package stelnet.board.storage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.LinkedList;
import java.util.List;
import stelnet.widget.viewer.DisplayStrategyButton;
import stelnet.widget.viewer.GroupingStrategy;
import stelnet.widget.viewer.MarketView;
import stelnet.widget.viewer.MarketViewState;
import uilib.EventHandler;
import uilib.Renderable;
import uilib.RenderableFactory;
import uilib.property.Size;

public class StorageView implements RenderableFactory {

    private final GroupingStrategy groupingStrategy;
    private final MarketViewState state;

    public StorageView(StorageState state) {
        groupingStrategy = state.getDisplayStrategy();
        this.state = state;
    }

    @Override
    public List<Renderable> create(Size size) {
        List<Renderable> renderables = new LinkedList<>();
        renderables.addAll(new MarketView(state).create(size));
        renderables.add(getNextStrategyButton(size));
        return renderables;
    }

    private Renderable getNextStrategyButton(Size size) {
        final GroupingStrategy nextStrategy = groupingStrategy.getNext();
        DisplayStrategyButton button = new DisplayStrategyButton(nextStrategy);
        button.setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(IntelUIAPI ui) {
                    StorageBoard board = StorageBoard.getInstance();
                    board.getState().setDisplayStrategy(nextStrategy);
                }
            }
        );
        return button;
    }
}
