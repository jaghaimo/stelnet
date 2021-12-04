package stelnet.board.storage;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.widget.viewer.ButtonManager;
import stelnet.widget.viewer.ContentRenderer;
import stelnet.widget.viewer.GroupingStrategy;
import stelnet.widget.viewer.MarketViewState;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class StorageState implements RenderableState, MarketViewState {

    private final ButtonManager filteringButtons = new ButtonManager();
    private ContentRenderer contentRenderer = ContentRenderer.ITEMS;
    private GroupingStrategy displayStrategy = GroupingStrategy.UNIFIED;

    @Override
    public List<Renderable> toRenderableList(Size size) {
        return new StorageView(this).create(size);
    }
}
