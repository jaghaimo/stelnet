package stelnet.board.storage;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import stelnet.widget.viewer.ContentRenderer;
import stelnet.widget.viewer.FilteringButtons;
import stelnet.widget.viewer.GroupingStrategy;
import stelnet.widget.viewer.RendererAwareState;
import uilib.Renderable;
import uilib.RenderableState;
import uilib.property.Size;

@Getter
@Setter
public class StorageState implements RenderableState, RendererAwareState {

    private final FilteringButtons filteringButtons = new FilteringButtons();
    private ContentRenderer activeRenderer = ContentRenderer.ITEMS;
    private GroupingStrategy activeStrategy = GroupingStrategy.UNIFIED;

    @Override
    public List<Renderable> toRenderables(Size size) {
        return new StorageView(this).create(size);
    }
}
