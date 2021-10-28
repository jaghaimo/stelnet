package stelnet.board.storage;

import com.fs.starfarer.api.ui.IntelUIAPI;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.board.storage.view.StorageTabContainer;
import stelnet.view.market.ContentRenderer;
import stelnet.view.market.DisplayStrategyButton;
import stelnet.view.market.FilteringButtons;
import stelnet.view.market.GroupingStrategy;
import uilib.EventHandler;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class StorageState implements RenderableState {

    private final FilteringButtons filteringButtons = new FilteringButtons();
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
        return Arrays.<Renderable>asList(new StorageTabContainer(this).create(size), button);
    }
}
