package stelnet.board.storage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.storage.view.StorageTabViewFactory;
import stelnet.view.market.ButtonManager;
import stelnet.view.market.ContentRenderer;
import stelnet.view.market.DisplayStrategyButton;
import stelnet.view.market.FilterManager;
import stelnet.view.market.GroupingStrategy;
import uilib.EventHandler;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class StorageState implements RenderableState {

    private final FilterManager filterManager = new FilterManager();
    private final ButtonManager buttonManager = new ButtonManager(filterManager);
    private ContentRenderer activeRenderer = ContentRenderer.ITEMS;
    private GroupingStrategy activeStrategy = GroupingStrategy.UNIFIED;

    @Override
    public List<Renderable> toRenderables(Size size) {
        final GroupingStrategy nextStrategy = activeStrategy.getNext();
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
        return Arrays.<Renderable>asList(new StorageTabViewFactory(this).createContainer(size), button);
    }
}
