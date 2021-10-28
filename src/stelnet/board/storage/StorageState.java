package stelnet.board.storage;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.widget.market.ContentRenderer;
import stelnet.widget.market.FilteringButtons;
import stelnet.widget.market.GroupingStrategy;
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
        return new StorageView(this).create(size);
    }
}
