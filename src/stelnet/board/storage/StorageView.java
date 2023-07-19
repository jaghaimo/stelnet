package stelnet.board.storage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.LinkedList;
import java.util.List;
import stelnet.util.StelnetHelper;
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

    public StorageView(final StorageState state) {
        groupingStrategy = state.getDisplayStrategy();
        this.state = state;
    }

    @Override
    public List<Renderable> create(final Size size) {
        final List<Renderable> elements = new LinkedList<>();
        elements.addAll(new MarketView(state).create(size));
        elements.add(getNextStrategyButton());
        return elements;
    }

    private Renderable getNextStrategyButton() {
        final GroupingStrategy nextStrategy = groupingStrategy.getNext();
        final DisplayStrategyButton button = new DisplayStrategyButton(nextStrategy);
        button.setHandler(
            new EventHandler() {
                @Override
                public void onConfirm(final IntelUIAPI ui) {
                    final StorageBoard board = StelnetHelper.getInstance(StorageBoard.class);
                    board.getRenderableState().setDisplayStrategy(nextStrategy);
                }
            }
        );
        return button;
    }
}
